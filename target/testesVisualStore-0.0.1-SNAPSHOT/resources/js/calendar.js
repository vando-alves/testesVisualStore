var funcoesjs_versao;
funcoesjs_versao = '1.00.00';

var vmxcalendar_dia = 0;
var vmxcalendar_mes = 0;
var vmxcalendar_ano = 0;
var vmxcalendar_objeto = 0;
var vmxcalendar_calendarDiv = false;
var vmxcalendar_offsetTop = 0;		
var vmxcalendar_offsetLeft = 0;	

function displayCalendar(objeto, formato, chamador) {

   vmxcalendar_chamarcalendario(objeto);
   
}

function  vmxcalendar_getTopPos(inputObj)
{

  var returnValue = inputObj.offsetTop + inputObj.offsetHeight;
  while((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetTop;
  return returnValue + vmxcalendar_offsetTop;
}

function vmxcalendar_getleftPos(inputObj)
{
  var returnValue = inputObj.offsetLeft;
  while((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetLeft;
  return returnValue + vmxcalendar_offsetLeft;
}

function vmxcalendar_positionCalendar(inputObj)
{
    if (!vmxcalendar_calendarDiv)  {
	     vmxcalendar_calendarDiv = document.createElement('DIV');
	     vmxcalendar_calendarDiv.id = 'vmxcalendar_calendario';
	     vmxcalendar_calendarDiv.style.zIndex = 1000;
		 vmxcalendar_calendarDiv.style.position = 'absolute'
		 document.body.appendChild(vmxcalendar_calendarDiv);
	}
	
	
	vmxcalendar_calendarDiv.style.visibility = 'visible';
	vmxcalendar_calendarDiv.style.display = 'block'; 
	vmxcalendar_calendarDiv.style.left = vmxcalendar_getleftPos(inputObj) + 'px';
	vmxcalendar_calendarDiv.style.top = vmxcalendar_getTopPos(inputObj) + 'px';

	
}

function vmxcalendar_daysInMonth(Month, Year)
 {
     return new Date(Year, Month,0).getDate();
 }

function vmxcalendar_somar(dia, mes, ano) {
  var data = new Date(vmxcalendar_ano+ano,(vmxcalendar_mes-1)+mes,vmxcalendar_dia+dia);
  vmxcalendar_gerarcalendario(data.getDate() + '/' + (data.getMonth()+1) + '/' + data.getFullYear());
  
}

function vmxcalendar_diaselecionado(dia) {
  if (dia == 99) {
	 vmxcalendar_calendarDiv.style.display = 'none'; 
     vmxcalendar_calendarDiv.style.visibility = 'none';
     vmxcalendar_calendarDiv.innerHTML = '';
  }
  if (dia < 1) {
     return;
	 }
  if (dia > 31) {
     return;
  }
  vmxcalendar_objeto.value = dia +'/'+vmxcalendar_mes+'/'+vmxcalendar_ano;
  vmxcalendar_calendarDiv.style.display = 'none'; 
  vmxcalendar_calendarDiv.style.visibility = 'none';
  vmxcalendar_calendarDiv.innerHTML = '';
}

function vmxcalendar_chamarcalendario(objeto) {
   vmxcalendar_positionCalendar(objeto);
   vmxcalendar_objeto = objeto;
   vmxcalendar_gerarcalendario(objeto.value);   
}

function vmxcalendar_gerarcalendario(parametro) {

  var hoje = new Date();
  vmxcalendar_dia = hoje.getDate();
  vmxcalendar_mes = hoje.getMonth()+1;
  vmxcalendar_ano = hoje.getFullYear();

  if (parametro && parametro.replace(/[/]/g, "").trim().length != 0) {
	  var barras = parametro.split("/");
	  if (barras.length == 3) {
		 vmxcalendar_dia = parseInt(barras[0]);
		 vmxcalendar_mes = parseInt(barras[1]);
		 vmxcalendar_ano = parseInt(barras[2]);	  
		 if ((vmxcalendar_dia < 1 || (vmxcalendar_dia > 31 ))){
			  vmxcalendar_dia = hoje.getDate();
		 }
		 if ((vmxcalendar_mes < 1 || (vmxcalendar_mes > 12 ))){
			  vmxcalendar_mes = hoje.getMonth()+1;
		 }
		 if ((vmxcalendar_ano < 1 || (vmxcalendar_ano > 9999 ))){
			  vmxcalendar_ano = hoje.getFullYear();
		 }
	  }
  }
		 
  var texto = '';
  var descdias = ['Domingo','Segunda','Terca','Quarta','Quinta','Sexta','Sabado']
  var descmes  = ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'];
  
  texto = texto + '<center><table border=0><tr><td><input type="button" class="vmxcalendar_button" value="<<" onclick="vmxcalendar_somar(0,0,-1)" ></td><td><input type="button" class="vmxcalendar_button" value="<" onclick="vmxcalendar_somar(0,-1,0)"></td>';
  texto = texto + '<td><div class="vmxcalendar_mes"><center>' + descmes[vmxcalendar_mes-1] + '/' + vmxcalendar_ano + '</center></div></td>';
  texto = texto + '<td><input type="button"  class="vmxcalendar_button" value=">" onclick="vmxcalendar_somar(0,1,0)"></td><td><input type="button"  class="vmxcalendar_button" value=">>" onclick="vmxcalendar_somar(0,0,1)"></td></tr></table></center>';

  var diasmes = vmxcalendar_daysInMonth(vmxcalendar_mes,vmxcalendar_ano);
  var dias=new Array();
  for (x=0;x<42;x++) {
      dias[x] = '';
  }
  var primeirodia = (new Date(vmxcalendar_ano,vmxcalendar_mes-1,1).getDay()) - 1;
  
  for (x=1;x<=diasmes;x++) {
      dias[primeirodia+x] = x;
  }
  texto = texto + '<center><table class="vmxcalendar_table" border=0><tr><td class="vmxcalendar_header">Dom</td><td class="vmxcalendar_header">Seg</td><td class="vmxcalendar_header">Ter</td><td class="vmxcalendar_header">Qua</td><td class="vmxcalendar_header">Qui</td><td class="vmxcalendar_header">Sex</td><td class="vmxcalendar_header">Sab</td></tr>';
  var colunas = 0;
  var linha = '';
  for (x=0;x<42;x++) {
	  if (colunas == 7) {
         texto = texto + '<tr>' + linha + '</tr>';
		 linha = '';
		 colunas = 0;
	  }
      colunas = colunas + 1;
	  if (dias[x] == '') {
             linha = linha + '<td class="vmxcalendar_corpo"><center><div >&nbsp;' + dias[x] + '&nbsp;</div></center></td>';
	  } else {
         if ((vmxcalendar_ano == hoje.getFullYear()) && (vmxcalendar_mes == hoje.getMonth()+1) &&  (hoje.getDate()==dias[x])) {
             linha = linha + '<td class="vmxcalendar_corpo_hoje"><center><div onclick="vmxcalendar_diaselecionado(' + dias[x] + ')">&nbsp;' + dias[x] + '&nbsp;</div></center></td>';
	     } else {
             linha = linha + '<td class="vmxcalendar_corpo"><center><div onclick="vmxcalendar_diaselecionado(' + dias[x] + ')">&nbsp;' + dias[x] + '&nbsp;</div></center></td>';
	     }
	  }
  }
  texto = texto + '<tr>' + linha + '</tr></table></center>';
  texto = texto + '<center><table border=0><tr><td><input type="button" class="vmxcalendar_button_rodape" value="Hoje" onclick="vmxcalendar_gerarcalendario(false)" ></td><td><input type="button" class="vmxcalendar_button_rodape" value="Fechar" onclick="vmxcalendar_diaselecionado(99)"></td></tr></table></center>';
    
  vmxcalendar_calendarDiv.innerHTML = texto;
}

