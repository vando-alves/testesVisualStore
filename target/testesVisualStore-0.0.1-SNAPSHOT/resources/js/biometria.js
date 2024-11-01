const POS_PORT_BIO_SYS = 1;
const TIMEOUT = 45000;
const CAPTURAR_TEMPLATE = 3;
const POS_TIPO_LOGIN = 2;

var biometriaInstance = null;
var sensorAtivo = false;
var PORTA_BIOMETRIA_SISTEMA = 9000;
var URL_SYS = "";
var cookieBio = getCookie("bioVmix");
const TIPO_LOGIN = getTipoLogin();

// Enum com os códigos dos erros
var Enum = {
	LOGIN_SOMENTO_POR_BIOMETRIA: 1,
	TIMEOUT: 5,
	LEITOR_EM_USO: 100,
	SENSOR_DESCONECTADO : 4, 
	GENERICO: 99,
	SUCESSO: 0
};

function getTipoLogin(){
	if(cookieBio !== null){
		return cookieBio.split("@@")[POS_TIPO_LOGIN];
	}
	 return 0;
}

function Biometria() {
	// Pegando o cookie
	if(cookieBio !== null) PORTA_BIOMETRIA_SISTEMA = cookieBio.split("@@")[POS_PORT_BIO_SYS];
	
	URL_SYS = "http://localhost:" + PORTA_BIOMETRIA_SISTEMA + "/biometria";
	this.url = URL_SYS;

	this.timeout = TIMEOUT;
	this.jsonRequest = null;
	
	this.setUrlWsCentral = function(urlWsCentral) {
		this.urlWsCentral = urlWsCentral;
	};
	
	this.initCaptura = function(callbackFim, callback) {
		this.callbackFim = callbackFim;
		this.callback = callback;
		
		var settings = {
			"crossDomain": true,
			"method": "POST",
			"headers": {
				"content-type": "application/json",
			}
		};
		
		settings.url = this.url;
		this.settingsRequest = settings;
		this.firstRequest = true;
		
		if (biometriaInstance) {
			biometriaInstance.abort = true;
			biometriaInstance.callbackFim = callbackFim;
			biometriaInstance.callback = callback;
			return;
		}
		
		biometriaInstance = this;
		
		this.call('bio_inicializar', CAPTURAR_TEMPLATE, this.codOperador);
	};
	
	this.sensorAbort = function(callbackFim) {
	 if (biometriaInstance) biometriaInstance.abort = true; 
	 if(callbackFim) callbackFim();
	};
	
	this.call = function(acao, operacao, codOperador, jsonRequest) {		
		var json = {
			"acao" : acao,
			"operacao" : operacao,
			"codOperador" : codOperador
		};
		
		json.timeout = this.timeout;
		json.urlWsCentral = this.urlWsCentral;

		if (jsonRequest) {
			json = jsonRequest;
		}
	
		this.settingsRequest.data = JSON.stringify(json);
		var request = $.ajax(this.settingsRequest);	

		var self = this;
		request.done(function (response) {
			if (self.callback) {
				self.callback(response);
			}
			setTimeout(function(){
				if (acao === 'bio_finalizar') {
					biometriaInstance = null;
					sensorAtivo = false;
					if (self.startAgain ) {
						setTimeout(function() {
							self.startAgain  = false;
							self.initCaptura(self.callbackFim, self.callback);
						}, 1000);
						
						return;
					}
					self.callbackFim(response);
					return;
				}
				
				if (self.abort) {
					self.call("bio_finalizar", operacao);
					return;
				}
				
				if (response.continuar || (acao === 'bio_inicializar' && response.status == 0)) {		
					self.call("bio_continuar", operacao);
					return;
				} 
						
				if (acao !== "bio_finalizar") {
					if (response.status == 101) {
						self.startAgain = true;
					}
					self.call("bio_finalizar", operacao);
					return;
				}	
			}, 1000);

		});
				
		request.fail(function (jqXHR, exception) {
			var resp = {};
			resp.status = Enum.GENERICO;
			resp.message = "Nao foi possivel se comunicar com o servico de biometria";
			$("#mensagemErro").html("Nao foi possivel se comunicar com o servico de biometria");
			self.callbackFim(resp);
		});
	}
}
 
function alterarLayoutTela() {
  
	var statusBio = "true";
	var modulo    = getTipoLogin();
	var func = {f(val1, val2){vHide(val1, val2);}};
	var funcFinal = {f(){stopSensor();}};
	var elementsScreen = [$("#btnEnviar"), $("#senha"), $("#rotuloSenha"), $("#btnLimpar")];
	var elementsScreenDefaultValue = [$("#senha")];

	$("#lblMensagem").html("");
	
	$('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/cadeado.svg)');
	$("#btnAtivarSensor").css('visibility', 'visible');
	$("#msgBiometria").show();
	
	if(TIPO_LOGIN == Enum.LOGIN_SOMENTO_POR_BIOMETRIA){
		elementsScreen.push($("#usuarios"));
		elementsScreen.push($("#rotuloUsuarios"));
		elementsScreenDefaultValue.push($("#usuarios"));
	}
	
	if(!document.getElementById("bio").checked){
		$("#btnAtivarSensor").css('visibility', 'hidden');
		$("#msgBiometria").hide();
		func = {f(val1, val2){vShow(val1, val2);}};
		statusBio = "false";
	}
	$("#msgBiometria").html("Click para ativar o sensor.");
	func.f(elementsScreen, elementsScreenDefaultValue);
	funcFinal.f();
	document.cookie = "STATUS_SWITCH_BIOSTATUS=" + statusBio + "; expires= 31536000; path=/" + "";
}

function vHide(listElementsVisible, listElementsDefaultValues){
	if(listElementsVisible == null) return;
	for(var i = 0; i < listElementsVisible.length; i++){
		listElementsVisible[i].hide();
	}
	for(var i = 0; i < listElementsDefaultValues.length; i++) {
		listElementsDefaultValues[i].val("000");
	}
}

function vShow(listElementsVisible, listElementsDefaultValues){
	if(listElementsVisible == null) return;
	for(var i = 0; i < listElementsVisible.length; i++) {
		listElementsVisible[i].show();
	}
	for(var i = 0; i < listElementsDefaultValues.length; i++) {
		listElementsDefaultValues[i].val("");
	}
}

function stopSensorServer(){
		var settings = {
			"crossDomain": true,
			"method": "GET",
			"headers": {
				"content-type": "application/json",
			}, 
			"url":  "http://localhost:" + PORTA_BIOMETRIA_SISTEMA + "/biometria/leitor/abort"
		};	
		var request = $.ajax(settings);
		request.done(function (response){});
			
		request.fail(function (jqXHR, exception) {
			var resp = {};
			resp.status = Enum.GENERICO;
			resp.message = "Nao foi possivel se comunicar com o servico de biometria";
		});
}

function stopSensor(){
	var biometria = new Biometria();
	biometria.sensorAbort();
}

function loginBiometriaWithTimer(whoCalls, timer) {

}

function loginBiometria(whoCalls) {

	var bioVmix = getCookie('bioVmix');
	var infos = bioVmix.split("@@");
	
	var biometria = new Biometria();
	sensorAtivo = true;
	if(whoCalls == 'liberacao'){
		$("#srcbtnAtivarSensor").prop('src', '/vm_visualstore_adm/resources/images/dedo.svg');
	}
	$('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/dedo.svg)');
	
	biometria.initCaptura(function(response){
		
		$("#msgBiometria").html("");
		if(response.status == Enum.TIMEOUT){
			setTimeout(TIMEOUT, loginBiometria(whoCalls));
			return;
		}
		
		if (response.status != Enum.SUCESSO && response.status != Enum.LEITOR_EM_USO) {
			desbloquear();
			$('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/falha.svg)');
			setTimeout(function() {
				$('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/cadeado.svg)');
				$('#msgBiometria').text("Clique para ativar o sensor.");
				$('#msgBiometria').removeAttr("hidden");
			}, 3000);
			if(whoCalls == 'liberacao'){
				$("#srcbtnAtivarSensor").prop('src', '/vm_visualstore_adm/resources/images/falha.svg');
				setTimeout(function() {
					$("#srcbtnAtivarSensor").prop('src', '/vm_visualstore_adm/resources/images/cadeado.svg');
					$('#msgBiometria').text("Clique para ativar o sensor.");
					$('#msgBiometria').removeAttr("hidden");
				}, 3000);
			}
			$("#lblMensagem").html(response.message);
			return;
		}
		
		if (!response.data){
			if(whoCalls == 'login' && document.getElementById("bio").checked){
				$("#msgBiometria").html("Clique para ativar o sensor.");
			}
			desbloquear();
			return;
		}
	
		if(infos[2] == 0){
			if (document.frmTela.txtUsuario.value == "") {
				 $("#lblMensagem").html("Usuário Inválido");
				 if(whoCalls == 'liberacao'){
						$("#srcbtnAtivarSensor").prop('src', '/vm_visualstore_adm/resources/images/cadeado.svg');
					}
					$('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/cadeado.svg)');
			      desbloquear();
			      return
			 }
		}
		
		if(whoCalls == 'liberacao'){
			$("#srcbtnAtivarSensor").prop('src', '/vm_visualstore_adm/resources/images/sucesso.svg');
		}
		// Adiciona o template capturado no input do form
		$($("[name=txtTemplate]")).val(response.data);

		if(whoCalls == 'liberacao'){
			$("[name=btnOk]").click();
		}else {
			$("form[name='frmTela']").submit();
		}
		setTimeout(desbloquear, 5000);
	}, function(resp){
		if(resp.mensagemOperador == "Impressao digital capturada.") {
			bloquear("Autenticando biometria...");
		}
		$("#msgBiometria").html(resp.mensagemOperador);
		console.log(resp);
	});
}

window.onbeforeunload = function() {
	if(sensorAtivo) {
		stopSensorServer();
		return false;
	}
}

setTimeout(function() {
	$("#btnAtivarSensor").click(function(event){
		event.preventDefault();
		ativarSensor();
	});
},1500);



function callDecision(){
	 $('#msgBiometria').html("Biometria não encontrada.");
	 $('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/falha.svg)');
	 setTimeout(function() {
		 if(!sensorAtivo){
			 $('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/cadeado.svg)');
			 $('#msgBiometria').html("Clique para ativar o sensor.");
		 }
	 }, 3000);
}
	 