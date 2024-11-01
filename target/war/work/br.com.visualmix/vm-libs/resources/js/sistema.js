var ua = navigator.userAgent.toLowerCase();
var check = function(r) {
    return r.test(ua);
};
var DOC = document;
var isStrict = DOC.compatMode == "CSS1Compat";
var isOpera = check(/opera/);
var isChrome = check(/chrome/);
var isWebKit = check(/webkit/);
var isSafari = !isChrome && check(/safari/);
var isSafari2 = isSafari && check(/applewebkit\/4/); // unique to
// Safari 2
var isSafari3 = isSafari && check(/version\/3/);
var isSafari4 = isSafari && check(/version\/4/);
var isIE = !isOpera && check(/msie/);
var isIE7 = isIE && check(/msie 7/);
var isIE8 = isIE && check(/msie 8/);
var isIE6 = isIE && !isIE7 && !isIE8;
var isGecko = !isWebKit && check(/gecko/);
var isGecko2 = isGecko && check(/rv:1\.8/);
var isGecko3 = isGecko && check(/rv:1\.9/);
var isBorderBox = isIE && !isStrict;
var isWindows = check(/windows|win32/);
var isMac = check(/macintosh|mac os x/);
var isAir = check(/adobeair/);
var isLinux = check(/linux/);
var isSecure = /^https/i.test(window.location.protocol);
var isIE7InIE8 = isIE7 && DOC.documentMode == 7


var indice;
var caminho;
var menuatual;
var funcao;
indice = "123456789ABCDEFGHIJKLMNOPQRSTUVXYZ";


function shadow(objeto,nome) {
   var aux = document.getElementById(nome + '_shadow');
   if (aux) {
     aux.style.height = objeto.offsetHeight + 'px';
     aux.style.width = objeto.offsetWidth + 'px';
     aux.style.top = (objeto.offsetTop + 3) + 'px';
     aux.style.left = (objeto.offsetLeft + 3) + 'px';
	 aux.style.visibility='';
   }
}

function resizeIframe() {  

    var ajuste = 0;
    var iframe = document.getElementById('desktop');
    var menu = document.getElementById('menu');
    var header = document.getElementById('header');
    var w = window.innerWidth
         || document.documentElement.clientWidth
         || document.body.clientWidth;    
	w = w - 135;
    w = (w < 0) ? 0 : w;

	if (isIE) {
	  ajuste = 2;
	}
    var h = window.innerHeight
         || document.documentElement.clientHeight
         || document.body.clientHeight;    

    h = h - 80;
    h = (h < 0) ? 0 : h;
    iframe.style.width = (w-ajuste) + 'px';
    iframe.style.height = (h-ajuste) + 'px';
	menu.style.height = (h+00) + 'px';
	menu.style.width = (115+ajuste) + 'px';
    header.style.width = (w + 122) + 'px';
    document.getElementById('framework').style.top = (h +60) + 'px';

	header.style.visibility='';
	menu.style.visibility='';
	iframe.style.visibility='';	
	
	if (isIE) {
	   shadow(header, "header");
	   shadow(menu, "menu");
	   shadow(iframe, "desktop");
	}
}


function mxc_evento(e)
{
	e = e? e: (window.event? window.event: null);

	this.posx = 0;
	this.posy = 0;

	if (e.pageX || e.pageY)
	{
		this.posx = e.pageX;
		this.posy = e.pageY;
	}
	else if (e.clientX || e.clientY)
	{
		this.posx = e.clientX + document.body.scrollLeft;
		this.posy = e.clientY + document.body.scrollTop;
	}

	if (e)
	{
		this.originalEvent = e;
		this.type = e.type;
		this.screenX = e.screenX;
		this.screenY = e.screenY;

		// IE  --> srcElement
		this.target = e.target? e.target: e.srcElement;

		// N4  --> modifiers
		if (e.modifiers)
		{
			this.altKey   = e.modifiers & Event.ALT_MASK;
			this.ctrlKey  = e.modifiers & Event.CONTROL_MASK;
			this.shiftKey = e.modifiers & Event.SHIFT_MASK;
			this.metaKey  = e.modifiers & Event.META_MASK;
		}
		else
		{
			this.altKey   = e.altKey;
			this.ctrlKey  = e.ctrlKey;
			this.shiftKey = e.shiftKey;
			this.metaKey  = e.metaKey;
		}

		// N4 --> which // N6 --> charCode
		this.charCode = !isNaN(e.charCode)? e.charCode: !isNaN(e.keyCode)? e.keyCode: e.which;
		this.keyCode = !isNaN(e.keyCode)? e.keyCode: e.which;
		this.button = !isNaN(e.button)? e.button: !isNaN(e.which)? e.which-1: null;
		this.debug = "charcode:" + e.charCode + " keycode:" + e.keyCode
     + " button:" + e.button + " wwhich:" + e.which;
	}

}

function keydowndocument(e) {
    var evento;
	   evento = new mxc_evento(e);
	   if ((evento.ctrlKey) && (evento.shiftKey)) {
		   callControl();
	   } else {
  	     if ((!evento.ctrlKey) && (!evento.shiftKey)) {
	        var el = document.getElementById('div_bloqueio');
  	        if (el.style.visibility != 'visible') {
	           el = document.getElementById('div_control');
  		       if (el.style.visibility != 'visible') {
  	  		      //Div de bloqueio nao esta ativo entao aciona o desktop novamente
                  try {
	               window.top.frames['desktop'].focarnoultimo();
	             } catch(e) {} 
  		       }
		    }
		 }
	  };
}  

function keydowndocument_original(e) {
    var evento;
	   evento = new mxc_evento(e);
	   if ((evento.ctrlKey) && (evento.shiftKey)) {
		   callControl();
	   }
}  


function fecharopcao() {
	desbloquear();
	var el;
	el = document.getElementById('div_control');
	el.innerHTML = "";
	el.style.visibility = 'hidden';
}





function desbloquear() {
   
   el = document.getElementById('iframe_bloqueio');
   el.style.visibility = 'hidden';
   
   el = document.getElementById('div_bloqueio');
   el.style.visibility = 'hidden';

   el = document.getElementById('div_mensagem');
   el.style.visibility = 'hidden';

}

function browsername() {

	var browser = "unknown";

	if (navigator.userAgent.indexOf("Opera") >= 0)
	 browser = "opera";
	else if (navigator.userAgent.indexOf("obot") >= 0)
	 browser = "robot";
	else if (navigator.appName.indexOf("etscape") >= 0)
	 browser = "netscape";
	else if (navigator.appName.indexOf("icrosoft") >= 0)
	 browser = "msie";

    return browser;
}

function bloquear(strmensagem) {
   
   if (strmensagem == 'null') {
      return;
   }

   if (browsername() == "msie")  {
      el = document.getElementById('iframe_bloqueio');
      el.style.height = '98%';
      el.style.width = '98%';
      el.style.visibility = 'visible';
   }
   
   el = document.getElementById('div_bloqueio');
   el.style.height = '100%';
   el.style.width = '100%';
   el.style.visibility = 'visible';

   el = document.getElementById('div_mensagem');

   if (strmensagem == '') {
       strmensagem = 'Aguarde processamento...';
   }

   el.style.visibility = 'visible';
   el.innerHTML = '<br>' + strmensagem +'<br><br>';
   el.style.width = '300';
   el.style.top  = (document.body.offsetHeight - 150) / 2;
   el.style.left = (document.body.offsetWidth - 300) / 2;
}


function waitajax(tipo) {
   
   el = document.getElementById('div_ajax');

   if (tipo == true) {   
      el.style.visibility = 'visible';
   } else { 
      el.style.visibility = 'hidden';
   }

}

function chamar(texto, alvo)
{
    //var el = window.top.frames[alvo].document['frmTela']
	//if (null!=el) {
    //   window.top.frames[alvo].document['frmTela'].evento.value = "atualizar"
    //   window.top.frames[alvo].document['frmTela'].submit()
    //}

    bloquear('');
	window.top.frames[alvo].location = texto
}

function showframe(url, w, h) {
   el = document.getElementById('iframe_modal');
   el.style.visibility = 'visible';
   el.style.width = w;
   el.style.height = h;
   el.style.top  = (document.body.offsetHeight - (h+20)) / 2;
   el.style.left = (document.body.offsetWidth - w) / 2;
   el.src = url;
   bloquear('');      
}

function closeframe() {      
   el = document.getElementById('iframe_modal');
   el.style.visibility = 'hidden';
   el.src = "about:blank";
   desbloquear('');   
}

function doResize() {
	var winW;
	var winH;
	var el;
	if (parseInt(navigator.appVersion)>3) {
	 if (navigator.appName=="Netscape") {
	  winW = window.innerWidth-18; //Desconta barra de rolagem
	  winH = window.innerHeight-18;
	 }
	 if (navigator.appName.indexOf("Microsoft")!=-1) {
	  winW = document.body.offsetWidth-22; //Desconta barra de rolagem
	  winH = document.body.offsetHeight-22;
	 }
	}
    //el = document.getElementById("teste") 
    //el.style.width = winW;
}

function menuopcao(opcao) {
	   fecharopcao();
	   var ind;
	   var x;
	   var achou = false;
	   for (ind=0;ind<menuatual.length;ind++) {
	      if (indice.substr(ind,1) == opcao.toUpperCase()) {
		      achou = true;
		      if (!menuatual[ind][1]) {
			     caminho = caminho + " - " + menuatual[ind][0];
	             var novomenu;
				 novomenu = [];
				 for (x=3;x<menuatual[ind].length;x++){
				    novomenu.push(menuatual[ind][x]);
				 }
				 menucall(novomenu);
	             return false;
			  } else {
			     funcao = new Function(menuatual[ind][1]);
				 funcao();
				 return true;
			  }
		      if (menuatual[ind][1] != null) {
	             return true;
			  } else {
			      menucall(menuatual[ind][1]);
			  }
		  }
	   }
	   if (!achou) {
	    try {
		  window.top.frames['desktop'].focarnoultimo();
		} catch(e) {}    
	   }
	   return true;
	  
	}

function menucall(pmenu) {   

 fecharopcao();
	
	if (!pmenu) {
	   menuatual = MENU_ITEMS;
	   caminho = "Principal";
	} else {
	   menuatual = pmenu;
	}
	
	var opcoes;
	var mais;
	//opcoes = "<center>Escolha a Opção</br>(" + caminho + ")</br></br></center><table>";
	opcoes = "<center>Menu  de Opções do Sistema</br>[ " + caminho + " ]</br></br></center><table>";
	for (x=0;x<menuatual.length;x++) {
	    if (!menuatual[x][1]) {
		   mais = "...";
		} else {
		   mais = "";
		 }
	    opcoes = opcoes + "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + indice.substr(x,1) + " - " + menuatual[x][0] + mais + "</td></tr>";		
	}
	opcoes = opcoes + "</table>";
// opcoes = opcoes + '<center><form name="frmopcaom">Informe a opção desejada:&nbsp;<input style="width:30px" type="text" name="txtopcaom" onkeyup="menuopcao(this.value)" /></form></center>'	;
 opcoes = opcoes + '<center><form name="frmopcaom" onsubmit="return false;">Informe a opção desejada:&nbsp;<input style="width:30px" type="text" name="txtopcaom" onkeyup="menuopcao(this.value)" /></form></center>'	;

 bloquear('');
 el = document.getElementById('div_control');
 el.style.visibility = 'visible';
 el.innerHTML = opcoes 
 tamanho = 100 + (menuatual.length * 20);	
	if (tamanho < 300) {
	    tamanho = 300;
	}
 el.style.width = '480';
 el.style.height = tamanho;
 el.style.top  = (document.body.offsetHeight - tamanho) / 2;
 el.style.left = (document.body.offsetWidth - 480) / 2;

 objeto =  document.forms['frmopcaom'].elements['txtopcaom'];
 try {
	   objeto.focus();
	   objeto.select();
 } catch(e) {} 

}

function menutelaopcao(opcao) {
	var menu;
	var i;
 fecharopcao();
	menu = window.top.frames['desktop'].getMenuTela();
	for (i=0;i<menu.length;i++) {
	    if (menu[i].accessKey) {
	       if (menu[i].accessKey.toUpperCase() == opcao.toUpperCase()) {
				if (menu[i].onclick) {
				   menu[i].onclick();
				   return;
				}
		   }
		}
	}
 try {
	  window.top.frames['desktop'].focarnoultimo();
	} catch(e) {}    
	
}

function menutelacall()  {
	
 fecharopcao();
	var i;
	var opcoes;
	var mais;
	var menu;
	var contador;
	//opcoes = "<center>Escolha a Opção</br>(" + caminho + ")</br></br></center><table>";
	opcoes = "<br><center>Menu de Opções da Tela</br></br></center><table>";

 try {
 	menu = window.top.frames['desktop'].getMenuTela();
  } catch(e) { return;} 
		
	if (!menu) {
	   return;
	}
	
	contador = 0;
	for (i=0;i<menu.length;i++) {
	    if (menu[i].accessKey) {
	       opcoes = opcoes + "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + menu[i].accessKey + " - " +  menu[i].innerHTML + "</td></tr>";		
		   contador = contador + 1;
		}
	}

	opcoes = opcoes + "</table>";
 opcoes = opcoes + '<center><form name="frmopcaot" onsubmit="return false;">Informe a opção desejada:&nbsp;<input style="width:30px" type="text" name="txtopcaot" onkeyup="menutelaopcao(this.value)" /></form></center>'	;

 bloquear('');
 el = document.getElementById('div_control');
 el.style.visibility = 'visible';
 el.innerHTML = opcoes 
 tamanho = 100 + (10 * 20);	
	if (tamanho < 300) {
	    tamanho = 300;
	}
 el.style.width = '480';
 el.style.height = tamanho;
 el.style.top  = (document.body.offsetHeight - tamanho) / 2;
 el.style.left = (document.body.offsetWidth - 480) / 2;

 objeto =  document.forms['frmopcaot'].elements['txtopcaot'];
 try {
	   objeto.focus();
	   objeto.select();
 } catch(e) {} 
	
}		

function keyupComboBox(nome, combo, e) {

 var indice;
 var evento;
	
	indice = combo.selectedIndex;
	evento = new mxc_evento(e);
	
	if (evento.keyCode==27) {
	   fecharopcao();
	    try {
	  	  window.top.frames['desktop'].focarnoultimo();
	  	} catch(e) {} 
	}

	if ((evento.keyCode==13) || (evento.keyCode==32)) { 
	   fecharopcao();
	   try {
	         window.top.frames['desktop'].setComboBoxById(nome,indice);
	   } catch(e) {};
	}	

}

function callComboBox(nome, opcoes) {

	var texto
	var el
	var objeto

	el = document.getElementById('div_bloqueio');
	if (el.style.visibility == 'visible') {
	    el = document.getElementById('div_control');
		if (el.style.visibility != 'visible') {
	  		//Se div de bloqueio ja esta ativa e nao e o div de controle entao sai da funcao
		  return false;
		}
	};
	nome = "'" + nome + "'";
	texto = '<div style="text-align:right;width:100%;"><a href="javascript:fecharopcao()">[Fechar]</a>&nbsp;</div>' + 
	         '<div style="text-align: center;height:30px;width:100%;">Selecione a opção desejada.</div>';
 texto = texto + '<form name="frmopcao" onsubmit="return false;">';
 texto = texto + '<select name="cmbopcao" size=5 style="width:396px;height:258px" onkeyup="keyupComboBox(' + nome + ',this, event)">';
	texto = texto + opcoes;
 texto = texto + '</select>';
 texto = texto + '</form>';
	
	if (texto == '') {
	  return false;
	}
	
 bloquear('');
 el = document.getElementById('div_control');
 el.style.visibility = 'visible';
 el.innerHTML = texto 

 if (el.innerHTML == '') {
	   return false;
 }
 
 el.style.width = '400';
 el.style.height = '305';
 el.style.top  = (document.body.offsetHeight - 300) / 2;
 el.style.left = (document.body.offsetWidth - 300) / 2;

 objeto =  document.forms['frmopcao'].elements['cmbopcao'];
 try {
	   objeto.focus();
	   objeto.select();
 } catch(e) {} 


}