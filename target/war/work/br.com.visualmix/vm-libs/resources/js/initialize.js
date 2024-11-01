var moduloBiometria = {
	BIOMETRIA_COM_USUARIO : 1,
	BIOMETRIA_SEM_USUARIO : 0
};

function parametrizar() {
	var cookieVmix = getCookie("bioVmix");

	if (cookieVmix !== null && cookieVmix.split("@@")[0] == "true") {
		showLayout();
		return;
	}
	
	if (cookieVmix !== null && cookieVmix.split("@@")[0] == "false") {
		return;
	}

	var maxAge = new Date();
	maxAge.setTime(maxAge.getTime() + (365 * 24 * 60 * 60));
	var requisicao = $
			.ajax( {
				cache : false,
				url : "http://localhost:9000/acoesbiometria?acao=visualstore_version&mensagem=versao"
			});

	requisicao.done(function(resp) {
		document.cookie = "bioVmix=true@@9000@@0; expires=" + maxAge
				+ "; path=/";
		document.cookie = "STATUS_SWITCH_BIOSTATUS=true; expires=" + maxAge + "; path=/";
		showLayout();
	});

	requisicao.fail(function(jqXHR, exception) {
		document.cookie = "bioVmix=false@@9000@@2; expires=maxAge; path=/";
		document.cookie = "STATUS_SWITCH_BIOSTATUS=false; expires=" + maxAge + "; path=/";
	});

}

function carregaUltimoUsuario() {
	var cookieUltimoUsuario = getCookie('ULTIMOACESSO');
	if (cookieUltimoUsuario != null) {
		var user = cookieUltimoUsuario.split("@#@");
		document.querySelector("#usuarios").value = user[0];
	}
}
function showLayout() {
	var cookieStatus = getCookie(COOKIE_STATUS);
	var cookieBio = getCookie(COOKIE_BIOVMIX);
	var modulo = cookieBio.split("@@");

	$("#btnLimpar").hide();
	$("#bioAtiva").show();
	$("#bio").show();
	$("#bio_span").show();
	$("#bio_label").show();
	$("#msgBiometria").show();
	$("#btnAtivarSensor").css('visibility', 'visible');
	
	if (cookieStatus == 'true'
			&& modulo[2] == moduloBiometria.BIOMETRIA_COM_USUARIO) {
		changeSwitch(true);
		hideComponents();
		$("#usuarios").hide();
		$("#rotuloUsuarios").hide();
		$("#usuarios").val("000");

	} else if (cookieStatus == 'true'
			&& modulo[2] == moduloBiometria.BIOMETRIA_SEM_USUARIO) {
		changeSwitch(true);
		hideComponents();
	} else {
		// mostrando com os componentes de senha
		changeSwitch(false);
		$("#btnLimpar").show();
		$("#senha").show();
		$("#rotuloSenha").show();
		$("#btnEnviar").show();
		$("#msgBiometria").hide();
		$("#btnAtivarSensor").css('visibility', 'hidden');
	}
}

function changeSwitch(statusSwitch) {
	$('#bio').attr("checked", statusSwitch);
}

function hideComponents() {
	$("#btnEnviar").hide();
	$("#senha").hide();
	$("#rotuloSenha").hide();
	$("#senha").val("000");
}

function ativarSensor() {
	if ($("#usuarios").val().trim() == "") {
		$("#lblMensagem").html("Informe um usuário para ativar o sensor");
		$("#usuarios").focus();
		return;
	}
	stopSensor();
	$("#lblMensagem").html("");
	if(sensorAtivo){
		setTimeout(function() {
			loginBiometria('login');
		}, 3000)
		return;
	}
	loginBiometria('login');
}

function activeBio() {
	var cookieStatus = getCookie("STATUS_SWITCH_BIOSTATUS");
	var cookieBio = getCookie("bioVmix");
	
	if(cookieStatus != null && cookieBio != null) {
		if ($("#usuarios").val().trim() != "" && cookieStatus == "true" && cookieBio.split("@@")[0] == "true") {
			$('#msgBiometria').show();
			$('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/cadeado.svg)');
			stopSensorServer();
			setTimeout(function() {
				$('#btnAtivarSensor').css('background-image','url(/vm_visualstore_adm/resources/images/biometria/dedo.svg)');
				loginBiometria('login');
			}, 3000);
		}
	}
}