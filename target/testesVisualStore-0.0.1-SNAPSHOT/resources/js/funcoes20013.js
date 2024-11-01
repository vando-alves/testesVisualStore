//FormatNumber(Expression, NumDigitsAfterDecimal, IncludeLeadingDigit,
//             UseParensForNegativeNumbers, GroupDigits)
//======================================================================
var VSEP1 = 'VSEP1' 
var funcoesjs_versao13;
funcoesjs_versao13 = '2.00.22';
//2.00.13 - Lefl
//Criacao de funcoes de digitacao para utilizacao na tela de acesso remoto ao pdv
//Criacao de opcao de receber imgsrc e rotina javascript como retorno de chamada ajax

//2.00.12 - Lefl
//Criacao da funcao ajaxloop para ficar em comunicacao online com o servidor
//para possiveis atualizacoes de tela (Criado inicialmente para tela de monitoramento do pdv)

//2.00.11 - Lefl
//Alteraca ja rotina ajaxid para suportar parametros adicionais e multiplos campos
//para envio para central
//Criacao da funcao datagridtreefold

//2.00.10 - Lefl
//Criacao de opccao de chamada de ajax com bloqueio de tela


//2.00.09 - Lefl
// Correcao de navegacao por enter que esta navegando errado quando uma grid possuia mais
// de 1 item. Foi criada a variavel i dentro da funcao navegacao.

//2.00.08 - Lefl
//Criacao de arquivo funcoes20008.js e no Body.java para forcar uso de nova versao do  modulo 


//2.00.07 - Lefl
// Criacao de arquivo funcoes20007.js e no Body.java para forcar uso de nova versao do  modulo 


//2.00.06 - Lefl
//2.00.05 - Lefl
// Criacao da funcao menutabF2Main() para encontrar tab principal da tela dinamicamente
// independente do nome da tab e correcoes na rotina getTabChild() para encontrar tabContro1


//2.00.04 - Lefl
//2.00.03 - Lefl 
//- Criacao da funcao setComboBoxById e callComboBoxById para utilizacao de chamada
//  de lista de opcoes de combobox atraves de F4
//

//2.00.02 - Lefl
//- Correcoes nas Funcoes para tratamento de navegacao por shitf+f2 nas tabs internas e
//  ctrl+f2 nas filhas das tabs internas
//

//2.00.01 - Lefl
// - Funcoes para tratamento de navegacao por shitf+f2 nas tabs internas e
//   ctrl+f2 nas filhas das tabs internas
//

function getNearestTableAncestor(htmlElementNode) {
    while (htmlElementNode) {
        htmlElementNode = htmlElementNode.parentNode;
        if (htmlElementNode.tagName.toLowerCase() === 'table') {
            return htmlElementNode;
        }
    }
    return undefined;
}

function setTBodyInnerHTML(id, html) {
	
	  var tbody = document.getElementById(id);

      var table = getNearestTableAncestor(tbody);

	  var tempdiv = tbody.ownerDocument.createElement('div');
	  tempdiv.innerHTML = '<table>' + ajaxreplace(html) + '</table>';

	  var tempTable = tempdiv.firstChild;

	  while (tempTable.childNodes.length > 0) {
			table.insertBefore(tempTable.childNodes[0], tbody);
	  } 
	  
	  tbody.style.display = '';

}

function ajaxreplace(valor) {
	
	return valor.replace(/\$1/gi, VSEP1).replace(/\$2/gi, "@").replace(/\$3/gi, "'").replace(/\$0/gi, "$");

}

function datagridtreedisplay(table, id, display) {
    for (x=0;x<table.rows.length;x++) {
	       tbodyid = table.rows[x].getAttribute('data-tbody');
		   if (tbodyid) {
		      if (tbodyid.length >= id.length) {
		          if (tbodyid.substring(0, id.length) == id) {
					  if (display == 'none') {
                         try {
					         table.rows[x].getElementsByTagName('INPUT')[0].value = '+';
		                     table.rows[x].getElementsByTagName('INPUT')[0].setAttribute('data-fold','+');
                             } catch(e) {} 
					  }
		              if (tbodyid.length == id.length) {
			              table.rows[x].style.display = display;
					  } else {
					      if (display == 'none') {					    	 
			                 table.rows[x].style.display = 'none';
						  } 
					  }
				  }
			  }
		   }
	  }	  
}

function datagridtreefold(botao, corpo, ajax) {
    
    try {
	   var el = document.getElementById(botao);
	   var tb = document.getElementById(corpo);
	   var callAjax = ajaxreplace(ajax).replace("style=@@@@", "style="+ tb.style.display).replace("data-ajax=@@@@", "data-ajax="+ tb.getAttribute('data-ajax'));
	   if ((el) && (tb)) {
	       if (el.getAttribute('data-fold') == '+') {
		       el.value = '-';
			   el.setAttribute('data-fold','-');
	           if (tb.getAttribute('data-ajax') == 'S') {
			       tb.setAttribute('data-ajax','N');
			   } else {
			       tb.style.display = '';
				   datagridtreedisplay(getNearestTableAncestor(tb), corpo, '');
			   }
		   } else {
		       el.value = '+';
			   el.setAttribute('data-fold','+');
			   tb.style.display = 'none';
			   datagridtreedisplay(getNearestTableAncestor(tb), corpo, 'none');
		   }
	       eval(callAjax);
	   }
    } catch(e) {} 
}

function setComboBoxById(Elemento, indice) {
	var el;
    try {
  	   el = document.forms[0][Elemento];
       document.forms[0][Elemento].selectedIndex=indice; 	
	   setFocusxx(Elemento);	
       if (el.onchange) {
     	   el.onchange();
       };
    } catch(e) {} 
	
}

function callComboBoxById(Elemento) {

	var combo;
	var opcoes;
	var selecionado;
	combo = document.forms[0].elements[Elemento]; 
	opcoes = '';
	for (var i = 0; i < combo.options.length; i++) {
	    if (combo.selectedIndex == i) {
		   selecionado = 'selected=""';
		} else {
		   selecionado = '';
		}
	    opcoes = opcoes + '<option value="' + combo.options[i].text + '" ' + selecionado +'>' + combo.options[i].text  + '</option>';
	}
	 window.parent.callComboBox(Elemento,opcoes);
}

function getMenuTela() {
	   return document.getElementById('Header_MenuPrincipal').childNodes;
	}


function FormatNumber(num,decimalNum,bolLeadingZero,bolParens,bolCommas)
/**********************************************************************
	IN:
		NUM - the number to format
		decimalNum - the number of decimal places to format the number to
		bolLeadingZero - true / false - display a leading zero for
							numbers between -1 and 1
		bolParens - true / false - use parenthesis around negative numbers
		bolCommas - put commas as number separators.

	RETVAL:
/**********************************************************************/
{ 
    if (isNaN(parseInt(num))) return "NaN";

	var tmpNum = num;
	var iSign = num < 0 ? -1 : 1;		// Get sign of number

	// Adjust number so only the specified number of numbers after
	// the decimal point are shown.
	tmpNum *= Math.pow(10,decimalNum);
	tmpNum = Math.round(Math.abs(tmpNum))
	tmpNum /= Math.pow(10,decimalNum);
	tmpNum *= iSign;					// Readjust for sign
	
	
	// Create a string object to do our formatting on
	var tmpNumStr = new String(tmpNum);

    if (decimalNum > 0) {
		iStart = tmpNumStr.indexOf(".");
		if (iStart < 0)  {
		   tmpNumStr = tmpNumStr + '.';
		   iStart = tmpNumStr.indexOf(".");
		   }
		tmpNumStr = tmpNumStr + '0000000000'
		tmpNumStr = tmpNumStr.substring(0,iStart) + tmpNumStr.substring(iStart,iStart+decimalNum+1)
		}
	// See if we need to strip out the leading zero or not.
	if (!bolLeadingZero && num < 1 && num > -1 && num != 0)
		if (num > 0)
			tmpNumStr = tmpNumStr.substring(1,tmpNumStr.length);
		else
			tmpNumStr = "-" + tmpNumStr.substring(2,tmpNumStr.length);

	// See if we need to put in the commas
	if (bolCommas && (num >= 1000 || num <= -1000)) {
		var iStart = tmpNumStr.indexOf(".");
		if (iStart < 0)
			iStart = tmpNumStr.length;

		iStart -= 3;
		while (iStart >= 1) {
			tmpNumStr = tmpNumStr.substring(0,iStart) + "," + tmpNumStr.substring(iStart,tmpNumStr.length)
			iStart -= 3;
		}		
	}

	// See if we need to use parenthesis
	if (bolParens && num < 0)
		tmpNumStr = "(" + tmpNumStr.substring(1,tmpNumStr.length) + ")";

    tmpNumStr = tmpNumStr.replace(",",":")
    tmpNumStr = tmpNumStr.replace(".",",")
    tmpNumStr = tmpNumStr.replace(":",".")

	return tmpNumStr;		// Return our formatted string!
}

function isNumber(numero){

   var CaractereInvalido = false;

   if (isEmpty(numero)) {
   	  return false;
   }


   for (i=0; i < numero.length; i++){
      var Caractere = numero.charAt(i);
      if(Caractere != "." && Caractere != "," && Caractere != "-"){
         if (isNaN(parseInt(Caractere))) CaractereInvalido = true;
      }
   }
   return !CaractereInvalido;
}

function ToFloat(numero){

   var Valido = false;
   var tmpNum = numero;

   tmpNum = tmpNum.replace(".","")
   tmpNum = tmpNum.replace(",",".")

   return parseFloat(tmpNum);

}


function strTrim(Dado)
  {
  var sDado, Result, i, f;

  Result = "";
  sDado = Dado.toString();
  if (sDado.length > 0)
    {
    for (i=0; i<sDado.length; i++)
      {
      if (sDado.charAt(i) != " ") break;
      }
    if (i < sDado.length)
      {
      for (f=sDado.length-1; f>=0; f--)
        {
        if (sDado.charAt(f) != " ") break;
        }
      Result = sDado.substring(i, f+1);
      }
    }
  return Result;
  }

function isSelected(form, objeto) {
	
    if (strTrim(getValue(form, objeto)) == "") {
		return false;
	} else {
		return true;
	}

}


function browsername() {

	if (navigator.userAgent.indexOf("Opera") >= 0)
	 return "opera";
	else if (navigator.userAgent.indexOf("obot") >= 0)
	 return "robot";
	else if (navigator.appName.indexOf("etscape") >= 0)
	 return "netscape"
	else if (navigator.appName.indexOf("icrosoft") >= 0)
	 return "msie"

    return "unknown";
}


function isFilled(pStrText) {
	return !isEmpty(pStrText);
}

function isEmpty(pStrText){
   var   len = pStrText.length;
   var pos;
   var vStrnewtext = "";

   for (pos=0; pos<len; pos++){
      if (pStrText.substring(pos, (pos+1)) != " "){
         vStrnewtext = vStrnewtext + pStrText.substring(pos, (pos+1));
      }
   }

   if (vStrnewtext.length > 0)
      return false;
   else
      return true;
}

function isTimeHHMMSS(pStrText) {

   var barras = pStrText.split(":");

   if ((!barras[0]) || (!barras[1]) || (!barras[2])) {
      return false;
	  }

   if (isNaN(barras[0])) {
      return false;
      }

   if (isNaN(barras[1])) {
      return false;
      }

   if (isNaN(barras[2])) {
      return false;
      }

   if ((barras[0] < 0) || (barras[0] > 23)) {
      return false;
	  }

   if ((barras[1] < 0) || (barras[1] > 59)) {
      return false;
	  }

   if ((barras[2] < 0) || (barras[2] > 59)) {
      return false;
	  }

   return true;
}

function isTimeHHMM(pStrText) {

   var barras = pStrText.split(":");

   if ((!barras[0]) || (!barras[1])) {
      return false;
	  }

   if (isNaN(barras[0])) {
      return false;
      }

   if (isNaN(barras[1])) {
      return false;
      }

   if ((barras[0] < 0) || (barras[0] > 23)) {
      return false;
	  }

   if ((barras[1] < 0) || (barras[1] > 59)) {
      return false;
	  }

   return true;
}

function isDate(aDate) {
		 /**
		 * A string passada para o construtor da classe SimpleDateFormat
		 * pode ser alterada conforme a formata??o desejada.
		 **/
		 //SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		 //Date date = null;
		 var dias = new Array(31,28,31,30,31,30,31,31,30,31,30,31);		 
		 var barras = new Array();
		 var intDia, intMes, intAno, DiasMax;	
		 
		 // **********************
		 // ** Valida??o de Data *
		 // **********************
		 barras = aDate.split("/");

		 if (barras.length != 3) {
			 return false;
		 }

		 // Validar formato correto de data
		 if ((!isNumber(barras[0])) || (!isNumber(barras[1])) || (!isNumber(barras[2]))) {
			 return false;
		 }

		 // Transforma vari?veis string para inteiro
		 intDia = barras[0];
		 intMes = barras[1];
		 intAno = barras[2];

		 // Mes
		 if ((intMes < 1 || (intMes > 12 ))){
			 return false;
		 }

		 // Ano
		 if ((intAno < 1 || (intAno > 9999))){
			 return false;
		 }

		 // Dia
		 DiasMax = dias[intMes-1];
		 if (intMes == 2 && (intAno % 4) == 0) {
			 //Se for fevereiro e ano bissexto soma mais um dia 
			 DiasMax++;; 
		 }
		 if ((intDia < 1 || (intDia > DiasMax))){
			 return false;
		 }			 

		 return true;

	}


function setSourceImg(Elemento, Valor) {
	
	   try {
		   el = document.getElementById(Elemento);
	       el.src = Valor;
		   } 
	   catch(e) {}
	
}

function setValuex(Elemento, Valor){

   var el;

   try {
	   el = document.getElementById(Elemento);
	   if (el) {
		   if (!el.type)
		   {  //Se type for indefinido entao ? um elemento DIV senao e elemento de Form
 el.innerHTML = Valor;
		   }
	      }
	   } 
   catch(e) {
	        }

   formul = document.forms[0];
   if (formul) {
      el = formul.elements[Elemento];
      if (el) {
         if (el.type == 'select-one') {
            setCombo(el, Valor);
         } else {         
            el.value = Valor;
         }
      }
   }

}

function setCombo(el, valor){

	var x;

    for (x=0;x<el.length;x++) {
       if (el.options[x].value == valor) {
          el.selectedIndex = x;
          return;
       }
    }    
}    


function setValue(Formulario, Elemento, Valor){

   var el;

   try {
	   el = document.getElementById(Elemento);
	   if (el) {
		   if (!el.type)
		   {  //Se type for indefinido entao ? um elemento DIV senao e elemento de Form
 el.innerHTML = Valor;
		   }
	      }
	   } 
   catch(e) {
	        }

   formul = document.forms[Formulario];
   if (formul) {
      el = formul.elements[Elemento];
      if (el) {
         if (el.type == 'select-one') {
            setCombo(el, Valor);
         } else {         
            el.value = Valor;
         }
      }
   }

}

function setFocus(Formulario, Elemento){

   var fomul;

   formul = document.forms[Formulario]; 

   if (formul) {
      objeto = formul.elements[Elemento]
      if (objeto) {
		  try {
	objeto.focus();
	objeto.select();
			  } catch(e) {} 
      }
   }

}

function setFocusx_old(Elemento){

   formul = document.forms[0]; 

   if (formul) {
      objeto = formul.elements[Elemento]
      if (objeto) {
		  try {
	objeto.focus();
	objeto.select();
			  } catch(e) {} 
      }
   }

}

function setFocusx(Elemento){
	  setFocusxx(Elemento);
}


function getValuex(Elemento){

	var formul, el;

   formul = document.forms[0]; 

   if (formul) {
      el = formul.elements[Elemento]
      if (el) {
         if (el.type == 'select-one') {
            if (el.selectedIndex < 0) {
               return "";
            } else {
               return el.options[el.selectedIndex].value;
            }
         } else {
            return el.value;
         }
      }
   }

   el = document.getElementById(Elemento);
   if (el) {
      return el.innerHTML;
      }

}

function getMaxLength(Elemento){

   var formul, el;

   formul = document.forms[0]; 

   if (formul) {
      el = formul.elements[Elemento]
      if (el) {
    	  try {
    		  if (el.maxLength) {
        		  return parseInt(el.maxLength);
    		  }
    	  } catch(e) {}
    	  try {
    		  if (el.getAttribute('data-maxlength')) {
        		  return parseInt(el.getAttribute('data-maxlength'));
    		  } 
    	  } catch(e) {}
      }
   }

   return -1;
   
}

function getValue(Formulario, Elemento){

	var formul, el;

   formul = document.forms[Formulario]; 

   if (formul) {
      el = formul.elements[Elemento]
      if (el) {
         if (el.type == 'select-one') {
            if (el.selectedIndex < 0) {
               return "";
            } else {
               return el.options[el.selectedIndex].value;
            }
         } else {
            return el.value;
         }
      }
   }

   el = document.getElementById(Elemento);
   if (el) {
      return el.innerHTML;
      }

}

function getFunction(Tela, Parametros) {
  window.open(Tela +'?'+Parametros,'tmpEvento', 'width=200,height=50,top='+((screen.height-50)/2)+',left='+((screen.width-200)/2)+',toolbar=no,scrollbars=no,resizable=no,menubar=no,status=no,directories=no,location=no');
}

function getFunctionIFrame(Tela, Parametros) {
   ifrservidor.location = Tela+'?'+Parametros;
}

//Testes
function getFunctionteste(Tela, Parametros) {
  http.open("GET", Tela +'?'+Parametros, true);
  http.onreadystatechange = handleHttpResponse;
  http.send(null);
}

function handleHttpResponse()
{
  if (http.readyState == 4) {
	alert(http.responseText.split(","));
  }
}

function getHTTPObject() {
	var req;

	try {
	 if (window.XMLHttpRequest) {
	  req = new XMLHttpRequest();

	  if (req.readyState == null) {
	   req.readyState = 1;
	   req.addEventListener("load", function () {
	   req.readyState = 4;

	   if (typeof req.onReadyStateChange == "function")
		req.onReadyStateChange();
	   }, false);
	  }

	  return req;
	 }

	 if (window.ActiveXObject) {
	  var prefixes = ["MSXML2", "Microsoft", "MSXML", "MSXML3"];

	  for (var i = 0; i < prefixes.length; i++) {
	   try {
		req = new ActiveXObject(prefixes[i] + ".XmlHttp");
		return req;
	   } catch (ex) {};

	  }
	 }
	} catch (ex) {}

	alert("XmlHttp Objects not supported by client browser");
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



function navega(e) {

	evento = new mxc_evento(e);

	if (evento.keyCode == 9)
	{
  	   achou  = false;
	   ant    = 0;
	   formul = evento.originalEvent.target.form;
	   nome   = evento.originalEvent.target.name;
	   for (x=0; x<formul.elements.length;x++)
	   {
		   if (achou)
		   {
			   if (formul.elements[x].type != 'hidden')
			   {
	  objeto = formul.elements[x];
	  try {
			objeto.focus();
			objeto.select();
		  } catch(e) {} 
	  return false;
			   }
		   } else 
		   {
			   if (formul.elements[x].name == nome)
			   {
	   if (evento.shiftKey)
	   {
		  objeto = formul.elements[ant];
		  try {
				objeto.focus();
			    objeto.select();
		      } catch(e) {} 
		  return false;
	   } else 
	   {
		  achou = true;
	   }
			   }
			   if (formul.elements[x].type != 'hidden')
			   {
	   ant = x;
			   }
		   }
	   }
	}
}

function getValor(linha,valor) {

		var valores
        var campos
        var x;
		
		valores = linha.toUpperCase().split("&");
		if (valores.length < 1) {
			return "";
		}

		for (x=0;x<valores.length;x++) {
			campos = valores[x].split("=");
			if (campos.length > 1) {
	if (campos[0] == valor.toUpperCase()) {
	   return campos[1];
	}
			}
		}
	
		return "";		
	}

//Testes


function getxmlhttp() {

	var retxmlhttp;

	try{
	    retxmlhttp = new XMLHttpRequest();
	}catch(ee){
	    try{
	        retxmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	    }catch(e){
	        try{
 retxmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	        }catch(E){
 retxmlhttp = false;
	        }
	    }
	}

	return retxmlhttp;
}	

//Fila de conex?es
xmlhttploop = getxmlhttp();
xmlhttp = getxmlhttp()
fila=[]
ifila=0

//Carrega via XMLHTTP a url recebida e coloca seu valor
//no objeto com o id recebido

function digitacao(cmd, campo, valor) {
	
	var conteudo = getValuex(campo);
    var maxlen   = getMaxLength(campo);
	
	if (cmd == 0) {
		//Adicionar valor
		conteudo = conteudo + valor;
		if ((maxlen < 0) || (conteudo.length <= maxlen)) {
			setValuex(campo, conteudo);			
		}
		return;
	}
	
	if (cmd == 1) {
		conteudo = valor;
		if ((maxlen < 0) || (conteudo.length <= maxlen)) {
			setValuex(campo, conteudo);			
		}
		return;
	}
	
	if (cmd == 2) {
		//Back Space
		if (conteudo.length <= 1) {
			conteudo = '';
		} else {
		    conteudo = conteudo.substring(0, conteudo.length-1);
		}
	    setValuex(campo, conteudo);
		return;
	}
	if (cmd == 3) {
		//Adicionar valor se o mesmo nao existir (.)
		if (conteudo.indexOf(valor) < 0) {
			conteudo = conteudo + valor;
			if ((maxlen < 0) || (conteudo.length <= maxlen)) {
				setValuex(campo, conteudo);			
			}
		}
		return;
	}

	
}

function ajaxloop(classe, beanid, funcao, parametro, paradicional) {

	var valor;
	var d = new Date();
	var parenv = '';
    var arrayParametro = parametro.split(VSEP1);

	if (arrayParametro.length <= 1) {
		if (parametro == '@form@') {
			   parenv = parametro + '&' + formserialize(document.forms[0]);
		} else {
			   if (parametro == '') {
				   parenv = ' ';		   
			   } else {
				   parenv = getValuex(parametro);		   
			   }			
		}
	} else {
       for (i = 0; i < arrayParametro.length; i++) {
	       parenv = parenv + getValuex(arrayParametro[i]) + VSEP1;
  	   }
	}
	
	var dados = "";
	var urls = "./Event.jsp?wdwtime=" + d.getTime() + "&" +
               "classe=" + classe + "&beanid=" + beanid + "&ajaxcall=call&funcao=" + funcao + 
               "&parametro=" + parenv +  paradicional;
	
    try {
    	  xmlhttploop.open("GET",urls,true);
       } 
    catch (e) {
       xmlhttploop = getxmlhttp();
       try {
       	     xmlhttploop.open("GET",urls,true);
           } 
        catch (e) {
   		     console.log('ajaxloop erro no open');
		     setTimeout("ajaxloop('"+classe+"','"+beanid+"','"+funcao+"','"+parametro+"','"+paradicional+"')",1000);
			 return;
          }    
    }    

    //Fun??o para tratamento do retorno
    Funcao =          " try { console.log('ajaxloop..:' + xmlhttploop.readyState); \n"
    Funcao = Funcao + "    if (xmlhttploop.readyState==4){ \n"
	Funcao = Funcao + "	     console.log('ajaxloop status ' + xmlhttploop.status); \n";
    Funcao = Funcao + "      if (xmlhttploop.status==200){ \n"
    Funcao = Funcao + "         ajaxretorno(xmlhttploop.responseText);\n";
    Funcao = Funcao + "         setTimeout(ajaxloop('"+classe+"','"+beanid+"','"+funcao+"','"+parametro+"','"+paradicional+"'),50); \n";
    Funcao = Funcao + "      } else { \n";
    Funcao = Funcao + "         setTimeout(ajaxloop('"+classe+"','"+beanid+"','"+funcao+"','"+parametro+"','"+paradicional+"'),100); \n";
    Funcao = Funcao + "      } \n";
    Funcao = Funcao + "    } \n";
    Funcao = Funcao + "} catch(e) { \n";
	Funcao = Funcao + "	   console.log('ajaxloop erro no readystate'); \n";
    Funcao = Funcao + "    setTimeout(ajaxloop('"+classe+"','"+beanid+"','"+funcao+"','"+parametro+"','"+paradicional+"'),1000); \n";
    Funcao = Funcao + "} \n";

    xmlhttploop.onreadystatechange= new Function(Funcao);
	xmlhttploop.onerror = function(e) {
   		      console.log('ajaxloop onerror');
		      setTimeout("ajaxloop('"+classe+"','"+beanid+"','"+funcao+"','"+parametro+"','"+paradicional+"')",1000);
			 };

	try {
        xmlhttploop.send(null)
	    } catch(e) {
		   console.log('ajaxloop erro no send');
		   setTimeout("ajaxloop('"+classe+"','"+beanid+"','"+funcao+"','"+parametro+"','"+paradicional+"')",1000);
		} 
}


function ajaxid(classe, beanid, funcao, parametro,acao,msg, paradicional) {
	ajaxid(classe, beanid, funcao, parametro,acao,msg, paradicional, false);
}

function getVal(val){
   return val;
}

function ajaxid(classe, beanid, funcao, parametro,acao,msg, paradicional,callMethod) {

	var pacao = "ajaxretorno";
	var pmsg = "";
	var valor;
	var d = new Date();
	var padicional = paradicional || '';
	var parenv = '';
	
    var arrayParametro = parametro.split(VSEP1);
    var func = getValuex;
    
    if(callMethod === true){
    	func = getVal;
	}
    
    console.log("callMethod : " + callMethod);
    
    console.log("Param : " + parametro);
    
    console.log("Funcão : " + func);
    
	if (arrayParametro.length <= 1) {
		if (parametro == '@form@') {
			   parenv = parametro + '&' + formserialize(document.forms[0]);
		} else {
			   if (parametro == '') {
				   parenv = ' ';		   
			   } else {
					parenv = func(parametro);		   
			   }			
		}
	} else {
       for (i = 0; i < arrayParametro.length; i++) {
	       parenv = parenv + func(arrayParametro[i]) + VSEP1;
  	   }
	}
	
	var dados = "";
	if(callMethod === true){
		dados = parenv;
		parenv = '';
	}
	
	var urls = "./Event.jsp?wdwtime=" + d.getTime() + "&" +
               "classe=" + classe + "&beanid=" + beanid + "&ajaxcall=call&funcao=" + funcao + 
               "&parametro=" + parenv + 
	           padicional;
	
    if (msg) {
    	if (msg != "") {
    		pmsg = msg;
    		pacao = "ajaxretornomsg";
    	}
    }

    if (acao) {
    	if (acao != "") {
    		pacao = acao;
    	}
    }

    fila[fila.length]=[pacao,urls,pmsg,dados];
    
    //Se n?o h? conex?es pendentes, executa
    if((ifila+1)==fila.length)ajaxRun();
}



function ajax(funcao,parametro,acao) {
	
    urls = "./Event.jsp?ajaxcall=call&funcao=" + funcao + "&parametro=" + getValuex(parametro);
    if (acao) {
       fila[fila.length]=[acao,urls,"",""];
    } else {
       fila[fila.length]=["ajaxretorno",urls,"",""];
    }
    //Se n?o h? conex?es pendentes, executa
    if((ifila+1)==fila.length)ajaxRun();
}

function ajaxretornomsg(retorno) {
	ajaxretornobloq(retorno, 1);
}

function ajaxretorno(retorno) {
	ajaxretornobloq(retorno, 0);
}

function ajaxretornobloq(retorno,desbloqueio) {
	
   var x;
   retorno = retorno.trim();
   parent.waitajax(false);
   if (desbloqueio == 1) {
	   unlockScreen();   
   }

   if (retorno == '') {
      return;
   }

   if (retorno.startsWith('VSEP1')) {
      ajaxretornosplit(retorno);
      return;
   }
   
   valores = eval('(' + retorno + ')');
   
   for (x=0;x<valores.length;x++) {
      if (valores[x].tipo == '1') {
         setValuex(valores[x].campo, valores[x].valor);
      }
      if (valores[x].tipo == '2') {
         loadCombo(valores[x].campo, valores[x].valor);
      }
      if (valores[x].tipo == '3') {
         submeter(0, valores[x].campo, true,'', valores[x].valor);
      }
      if (valores[x].tipo == '4') {
      	 setFocusx(valores[x].campo);
      }
      if (valores[x].tipo == '5') {
    	 setTBodyInnerHTML(valores[x].campo, valores[x].valor);
      }
      if (valores[x].tipo == '6') {
    	  try {
    		  eval(ajaxreplace(valores[x].valor));
 	      } catch (e) {
    		 console.log('ajax ' + e.message);	      		 
    	  }
    	  
      }
      if (valores[x].tipo == '7') {
    	  setSourceImg(valores[x].campo, valores[x].valor);    	  
      }
   }
   
}

function ajaxretornosplit(retorno) {
    var arrayPrincipal = retorno.split('VSEP1');
    var valores;
    for (i = 1; i < arrayPrincipal.length; i++) {
        valores = arrayPrincipal[i].split('@');
        if (valores.length > 2) {
	        valores[2] = ajaxreplace(valores[2]);                     
      	    if (valores[0] == '1') {
	           setValuex(valores[1], valores[2]);
	        }
	        if (valores[0] == '2') {
	           loadCombo(valores[1], valores[2]);
	        }
	        if (valores[0] == '3') {
	           submeter(0, valores[1], true,'', valores[2]);
            }
	        if (valores[0] == '4') {
	           setFocusx(valores[1]);
	        }
	        if (valores[0] == '5') {
	        	setTBodyInnerHTML(valores[1], valores[2]);
		    }
	        if (valores[0] == '6') {
	      	   try {
	      	 	  eval(ajaxreplace(valores[2]));
	      	   } catch (e) {
	      		 console.log('ajax ' + e.message);	      		 
	      	   }
	         }
	        if (valores[0] == '7') {
	      	  setSourceImg(valores[1], valores[2]);    	  
	        }
	        
	        
        }
    }
}

function ajaxCallx(funcao, parametro, retorno) {
  ajaxCall("jspauxiliar",funcao, parametro, retorno);
}

function ajaxCall(url, funcao, parametro, retorno){
    urls = "../" + url + "/Ajax.jsp?funcao=" + funcao + "&parametro=" + parametro;
    fila[fila.length]=[retorno,urls, "", ""];
    //Se n?o h? conex?es pendentes, executa
    if((ifila+1)==fila.length)ajaxRun();
}

//Executa a pr?xima conex?o da fila
function ajaxRun(){
    //Abre a conex?o

	var erro;
	var Funcao;
	var tipo = "GET";
	var dados = fila[ifila][3];

	if (dados) {
		if (dados != "") {
			tipo = "POST";
		}
	}
	
    erro = false;   
    try {
       xmlhttp.open(tipo,fila[ifila][1],true);
       } 
    catch (e) {
       //deu erro renova o arquivo de request
       xmlhttp = getxmlhttp();
       erro = true;
    }    
    if (erro) {
       xmlhttp.open(tipo,fila[ifila][1],true);
    }

   //Fun??o para tratamento do retorno
   Funcao = ""
   Funcao = Funcao + "if (xmlhttp.readyState==4){ \n"
   Funcao = Funcao + "   " + fila[ifila][0] + "(xmlhttp.responseText);\n";
   Funcao = Funcao + "   ifila++; \n";
   Funcao = Funcao + "   if(ifila<fila.length) { \n";
   Funcao = Funcao + "      setTimeout('ajaxRun()',20); \n";
   Funcao = Funcao + "     } else { \n";
   Funcao = Funcao + "      ifila=fila.length; \n";
   Funcao = Funcao + "     } \n";
   Funcao = Funcao + " } \n";

	xmlhttp.onreadystatechange= new Function(Funcao);
    //Executa
	if (fila[ifila][2] == "") {
	    parent.waitajax(true);
	} else {
		lockScreen(fila[ifila][2]);
	}
	
	if (tipo=="POST") {
	    xmlhttp.send(dados)
	} else {
	    xmlhttp.send(null)
	}
}

function getString(pStrText, pIndice) {

   var valores;	

   valores = pStrText.split(VSEP1 + VSEP1);

   if (valores[pIndice-1]) {
      return valores[pIndice-1];
   }

   return "";

}

function callPopUp(evento) {

  	   var topo = (screen.height - 600) / 2;
       var lefto = (screen.width - 800) / 2;
       var d = new Date();
       var nome = 'wdw' + d.getTime();
       window.open('Event.jsp?evento=' + evento ,nome,'scrollbars=yes,width=800,height=600,top=' + topo + ',left='+ lefto);

 }

 function callPopUpUrl(evento) {
  	   var topo = (screen.height - 480) / 2;
       var lefto = (screen.width - 640) / 2;
       var d = new Date();
       var nome = 'wdw' + d.getTime();
       window.open(evento ,nome,'scrollbars=yes,resizable=yes,width=640,height=480,top=' + topo + ',left='+ lefto);
 }

function lockScreen(msgproc) {
	 try { 
	parent.bloquear(msgproc);
	} catch(e) {} 
}

function unlockScreen() {
	 try { 
	parent.desbloquear();
	} catch(e) {} 
}

function clearCombo(combo) {
   var op
   op = document.forms[0].elements[combo];
   op.length = 1;
   op.options[0] = new Option('_________', '');
}

function replaceAll(string, token, newtoken) {
	while (string.indexOf(token) != -1) {
 		string = string.replace(token, newtoken);
	}
	return string;
}

function loadCombo(combo, texto) {

   var valores;
   var op;
   var x;
   
   if (texto == '') {
      op = document.forms[0].elements[combo];
      op.length = 0;
      return;
   }

   valores = eval('(' + replaceAll(texto,"%27","'") + ')');

   op = document.forms[0].elements[combo];
   op.length = valores.length;

   for (x=0;x<valores.length;x++) {
      op.options[x] = new Option(valores[x].text, valores[x].value);
   }

}

function vazio(v1, v2) {
}
// Fun��o do Componente MultiSelect
function multiSelect_Click(objeto, posicao, referencia){
	var check_valor = document.getElementById(referencia);
	var novoValor;
	var valor;
	valor = check_valor.value;
	if (valor.substring(posicao, posicao +1) == 1){
		novoValor = 0;
		objeto.className = "multiselect_item_off";
	}else{
		novoValor = 1;
		objeto.className = "multiselect_item_on";
	}
	check_valor.value = valor.substring(0, posicao) + novoValor + valor.substring(posicao + 1, valor.length);
}

function tabfocar(el) {
	   var objeto;
	   var ok;
	   var x;
	   if (el) {
	      for (x=0; el.childNodes[x];x++) {
		     objeto = el.childNodes[x];
		     if ((objeto.type) && (objeto.type != 'hidden')) {
				 ok = true;
		         try {
		  		   objeto.focus();
			     } catch(e) {ok = false;} 
			     if (ok) {
				     return true;
				 }
				 ok = true;
		         try {
		  		   objeto.focus();
			     } catch(e) {ok = false;} 
			     if (ok) {
				     return true;
				 }
			  } else {
			    if (tabfocar(objeto)) {
			      return true;
			    };
			  }
			}
	    }
		return false;
 }
		

function focarnoultimo() {
	t=setTimeout('focarnoultimox()',200);		
}
function focarnoultimox() {
    try { 
    	setFocusx(document.forms[0].foco.value);
	} catch(e) {} 		

}

function cancelarmodal() {

	parent.window.frames['desktop'].pesquisar.cancelou();
    parent.closeframe();
	focarnoultimo();

}

function confirmarmodal(retorno) {

	parent.window.frames['desktop'].pesquisar.selecionou(retorno);
    parent.closeframe();
	focarnoultimo();
}

function keypressdocument(e) {
	var targ;

	if (!e) var e = window.event;
	if (e.target) targ = e.target;
	else if (e.srcElement) targ = e.srcElement;
	if (targ.nodeType == 3) // defeat Safari bug
		targ = targ.parentNode;

	return navegacao(targ.name,new mxc_evento(e));		
}

function isNodeList(objeto) {
	  try {
			if (objeto.length >= 0)
			{
			    if ((objeto[0].type == 'radio') ||
				    (objeto[0].type == 'checkbox')) {
				   return true;
				}
			}
	  } catch(e){};
	  return false;
	}


function isVisible(e) {
	try {
	    while (e.nodeName.toLowerCase() != 'body' && e.style.display.toLowerCase() != 'none' && e.style.visibility.toLowerCase() != 'hidden') {
	        e = e.parentNode;
	    }
	    if (e.nodeName.toLowerCase() == 'body') {
	        return true;
	    } else{
	        return false;
	    }
	} catch(e){ return false};
}

function setFocusxx(Elemento) {

   formul = document.forms[0]; 
   
   if (formul) {
      objeto = formul.elements[Elemento];
      objeto2 = objeto;
	  if (isNodeList(objeto)) {
	    objeto = objeto2[0];
		if (objeto.type == 'radio') {
			try {
				for (i=0;i<objeto2.length;i++) {
				   if (objeto2[i].checked) {
					   objeto = objeto2[i];
					   break;
				   }
				}
			} catch(e) {} 
		}
      } 
      if (objeto) {
		  if (objeto.type == 'fieldset') {
			     return false;
		  }    	  
          if (objeto.type == 'hidden')  {
		      return false;
          }		   
		  if (!isVisible(objeto)) {
		      return false;
		  }
        //if (objeto.type == 'text')  {
    	//	  try {
        //    	  if (objeto.className == 'desabilitado') {
        //		      return false;
        //   	  }
        //    	  if (objeto.className == 'Desabilitado') {
        //		      return false;
        //    	  }
    	//	  } catch(e) {} ;
        //}		   
	      try {
            if (objeto.disabled == true) {
			   return false;
			   }
		  } catch(e) {} 
		  try {
			objeto.select();
		      } catch(e) {} 
		  try {
			objeto.focus(); 
			ultimocampofocado = Elemento;
			return true;
		      } catch(e) {} 
      }
   }
   
   return false;
}

function navegacao(objeto, event) {
    var focar;
	var x;
	var elemento;
    var campos;	
    var i;

	if ((event.keyCode!=32) && (event.keyCode!=13) && (event.keyCode!=27)) { 
		return false;
	}

	elemento = document.forms[0].elements[objeto]; 
	if (!elemento) {
		objeto = ultimocampofocado;
		elemento = document.forms[0].elements[ultimocampofocado];
	}
	if (isNodeList(elemento)) {
		if (elemento[0].type == 'radio') {
			try {
				for (i=0;i<elemento.length;i++) {
				   if (elemento[i].checked) {
					   elemento = elemento[i];
					   break;
				   }
				}
			} catch(e) {} 
		}
	} 
	
	campos = document.forms[0];
	focar = false;
    try {
	   event.cancelBubble = true;
	} catch(e) {} 
	if (event.stopPropagation) {
  	   event.stopPropagation();
	}

	if (event.keyCode==32) {		
	   try {
		  if ((!elemento.onmouseup) && (!elemento.ondblclick)) {
			  return false;
		  }
	      if (elemento.onmouseup) {
	    	  elemento.onmouseup();
	      };
	      if (elemento.ondblclick) {
	    	  elemento.ondblclick();
	      };
	   } catch(e) {};
       return true;
	}
	        
    if (event.keyCode==13) {
	    try {
	       if ((elemento.onkeydown) && (elemento.onclick)) {
	     	  elemento.onclick();
	 	       return true;
	       };
	    } 
	    catch(e) {};
 	       
		for (i=0;i<campos.length;i++) {
		   if (campos[i].name == objeto) {
		       focar = true;
		   }
		   if (focar) {
		     x = i+1;
			 if (x == campos.length) {
				for (x=0;x<i;x++) {
				   if (campos[x].name != objeto) {
						if (setFocusxx(campos[x].name)) {
						   break;
						}			 
					}
				}
				break;
			 }
			 if (campos[x].name != objeto) {
				if (setFocusxx(campos[x].name)) {
				   break;
				}			 
			}
		   }
		}
		return true;
	}
	
    if (event.keyCode==27) {
		for (i=campos.length-1;i>=0;i--) {
		   if (campos[i].name == objeto) {
		       focar = true;
		   }
		   if (focar) {
		     x = i-1;
			 if (x < 0) {
				for (x=campos.length-1;x>(i-1);x--) {
				   if (campos[x].name != objeto) {
						if (setFocusxx(campos[x].name)) {
						   break;
						}			 
					}
				}
				break;
			 }
			 if (campos[x].name != objeto) {
				if (setFocusxx(campos[x].name)) {
				   break;
				}			 
			}
		   }
		}  
		return true;
	}

	return false;
    	
}


var f2mainTab;
var f2shiftTab;
var f2ctrlTab;
var f2shiftKey;
var f2ctrlKey;
var f2mainKey;

var f2mainTab = '';
var f2shiftTab = '';
var f2ctrlTab = '';

f2shiftKey = '';
f2ctrlKey = '';
f2mainKey = '';

function callmenutabF2Main() {
	
    var al;
    var ind = 0;
	if (f2mainKey == '') {
	    al = getTabChild("TabControl",document);
 	    if (!al) {
      	   al = getTabChild("TabControl1",document);
	    }
	    if (al) {
	    	ind = parseInt(document.forms['frmTela'][al.id].value);
	    	if (ind) {
		    	eval("navegamenu" + al.id + "(" + ind +");");
	    	}
	   }
	}
}

function navegarmenutabf2shiftKey() {
  if (f2shiftKey == '') {
	  try {
	     callmenutabF2Main();
	  } catch(e) {};
  }
  if (f2shiftKey != '') {
     eval(f2shiftKey);
	 }
}
function navegarmenutabf2ctrlKey() {
  if (f2ctrlKey != '') {
     eval(f2ctrlKey);
	 }
}

function getTabChild(className, parentElement) {
    var children = parentElement.getElementsByTagName('TABLE');
	
	for (var i=0; i<children.length; i++) {
	   if (children[i].className == className) {
	      return children[i];
		  }
   }
   return false;
}

function menutabF2Main() {
	
    var al;
	if (f2mainKey == '') {
		f2mainKey = 'nothing';
		f2mainTab = 'nothing';
	    al = getTabChild("TabControl",document);
 	    if (!al) {
      	   al = getTabChild("TabControl1",document);
	    }
	    if (al) {
	    	f2mainKey = "navegamenu" + al.id + "(-1);";
	    	f2mainTab = al.id;
	   }
	}

	if (f2mainKey == 'nothing') {
		return;
	}
	
	eval(f2mainKey);
    
}

function menutabF2Child(Tabname, Elemento) { 

   var al
   var indice
   if (f2mainTab == 'nothing') {
      return;
   }
   if (f2mainTab == '') {
	   al = getTabChild("TabControl",document);
	   if (!al) {
     	   al = getTabChild("TabControl1",document);
	   }
	   if (al) {
	     f2mainTab = al.id;
	   } else {
	     f2mainTab = 'nothing';
	     return;
	   }
   }
   indice = 0;
   if (f2mainTab == Tabname) {
      indice = -1;
	  f2shiftTab = '';
	  f2shiftKey = '';
	  f2ctrlTab = '';
	  f2ctrlKey = '';
   }    
   if (f2shiftTab == Tabname) {
      indice = -2;
	  f2ctrlTab = '';
	  f2ctrlKey = '';
   }
   if (indice == 0) {
       return;
   }
   
   al = document.getElementById(Elemento);
   if (al) {
	   al = getTabChild("TabControl",al);
	   if (al) {
		  if (indice == -1) {
		      f2shiftTab = al.id;
			  f2shiftKey = "navegamenu" + al.id + "(-1);";
		  };
		  if (indice == -2) {
		      f2ctrlTab = al.id;
			  f2ctrlKey = "navegamenu" + al.id + "(-1);";
		  };
	   }
   }
}

function formserializebyname(name) {
	
	if (name) {
		return formserialize(document.forms[name]);			
	}
	
	return formserialize(document.forms[0]);			
	
}

function formserialize(form) {
	if (!form || form.nodeName !== "FORM") {
		return;
	}
	var i, j, q = [];
	for (i = form.elements.length - 1; i >= 0; i = i - 1) {
		if (form.elements[i].name === "") {
			continue;
		}
		switch (form.elements[i].nodeName) {
		case 'INPUT':
			switch (form.elements[i].type) {
			case 'text':
			case 'hidden':
			case 'password':
			case 'button':
			case 'reset':
			case 'submit':
				q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
				break;
			case 'checkbox':
			case 'radio':
				if (form.elements[i].checked) {
					q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
				}						
				break;
			case 'file':
				break;
			}
			break;			 
		case 'TEXTAREA':
			q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
			break;
		case 'SELECT':
			switch (form.elements[i].type) {
			case 'select-one':
				q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
				break;
			case 'select-multiple':
				for (j = form.elements[i].options.length - 1; j >= 0; j = j - 1) {
					if (form.elements[i].options[j].selected) {
						q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].options[j].value));
					}
				}
				break;
			}
			break;
		case 'BUTTON':
			switch (form.elements[i].type) {
			case 'reset':
			case 'submit':
			case 'button':
				q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
				break;
			}
			break;
		}
	}
	return q.join("&");
}

function mascara(e, objeto, mascara, alfanumerico){
    var keypress=(window.event)? e.keyCode : e.which;

    if(keypress < 32){
        return true;
    }

    campo = eval(objeto);

    saida = '';

    caracteres = '0123456789';
    if ((alfanumerico || (caracteres.search('[' + String.fromCharCode(keypress) + ']') != -1)) && campo.value.length < (mascara.length))
    {
        if(mascara.charAt(campo.value.length) != '#'){
            campo.value = campo.value + mascara.charAt(campo.value.length);
        }

    }else{
        //e.returnValue = false;
        StopEvent(e);
        return false;
    }
    return true;
}

function StopEvent(pE)
{
   if (!pE)
     if (window.event)
    pE = window.event;
     else
    return;
   if (pE.cancelBubble != null)
      pE.cancelBubble = true;
   if (pE.stopPropagation)
      pE.stopPropagation();
   if (pE.preventDefault)
      pE.preventDefault();
   if (window.event)
      pE.returnValue = false;
   if (pE.cancel != null)
      pE.cancel = true;
}


function digitaNumero(evento, objeto){
	var keypress = (window.event) ? event.keyCode : evento.which;
	campo = eval (objeto);

	saida = '';
	var caracteres = '0123456789';
	if (caracteres.search(String.fromCharCode(keypress)) == -1){
		event.returnValue = false;
	}
}