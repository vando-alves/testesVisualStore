var pesquisarjs_versao;
pesquisarjs_versao = '2.00.00';
var VSEP1 = 'VSEP1';
	
 function objpesquisar(nome) {
    this.campo = "";
    this.retorno = "";
    this.name = nome;
    this.titulo = "";
    this.qtdLinhas = 10;

    this.executarx = function(classe, largura, altura, id, campos) {
    	var d = new Date();
    	var lista = campos.split(";");
    	var valores = "";
    	var valor = "";
    	for(i=0;i<lista.length;i++){
    	    valor = getValuex(lista[i]);
    	    try {
	    		valor = valor.replace("$", "$0");
	    		valor = valor.replace(VSEP1, "$1");
	    		valor = valor.replace("@", "$2");
	    		valor = valor.replace(";", "$3");
	    		valor = valor.replace(":", "$4");
    	    } catch(e) {valor=""}; 
    		valores = valores + lista[i]+'@'+valor + ';';
    	}    	
		parent.showframe('./Event.jsp?classe=' + classe + '&evento=activate::'+id+';'+valores+'&wdwtime=' + d.getTime(), largura, altura);
    }
    
    this.executar = function(consulta, filtro, groupby, qtdLinhas) {
    	topo = (screen.height - 435) / 2;
        lefto = (screen.width - 580) / 2;
        if (filtro){
        	if (groupby){
        		if(qtdLinhas){
        			parent.showframe('br/com/visualmix/generico/jsppesquisar/Event.jsp?evento=executar::' + consulta + VSEP1 + filtro + VSEP1 + groupby + VSEP1 + qtdLinhas + '&objeto=' + this.name + '&titulo=' + this.titulo, 580, 435);
        		}else{
        			parent.showframe('br/com/visualmix/generico/jsppesquisar/Event.jsp?evento=executar::' + consulta + VSEP1 + filtro + VSEP1 + groupby + '&objeto=' + this.name + '&titulo=' + this.titulo, 580, 435);
        		}
        	}else{
        		if(qtdLinhas){
        			parent.showframe('br/com/visualmix/generico/jsppesquisar/Event.jsp?evento=executar::' + consulta + VSEP1 + filtro + VSEP1 + qtdLinhas + '&objeto=' + this.name + '&titulo=' + this.titulo, 580, 435);
        		}else{
        			parent.showframe('br/com/visualmix/generico/jsppesquisar/Event.jsp?evento=executar::' + consulta + VSEP1 + filtro + '&objeto=' + this.name + '&titulo=' + this.titulo, 580, 435);
        		}
        	}
        }else{
        	if(qtdLinhas){
        		parent.showframe('br/com/visualmix/generico/jsppesquisar/Event.jsp?evento=executar::' + consulta + '' + VSEP1 + '' + VSEP1 + '' + VSEP1 + qtdLinhas + '&objeto=' + this.name + '&titulo=' + this.titulo, 580, 435);
        	}else{
        		parent.showframe('br/com/visualmix/generico/jsppesquisar/Event.jsp?evento=executar::' + consulta + '&objeto=' + this.name + '&titulo=' + this.titulo, 580, 435);
        	}
        }
    }
    
    this.executarfiltro = function(consulta, filtro) {
  	    topo = (screen.height - 435) / 2;
        lefto = (screen.width - 580) / 2;
       this.janela = window.open('br/com/visualmix/generico/jsppesquisar/Event.jsp?evento=executar::' + consulta + VSEP1 + filtro + '&objeto=' + this.name + '&titulo=' + this.titulo,'Window'+ this.name,'scrollbars=yes,width=580,height=435,top=' + topo + ',left='+ lefto);
    }
    
    this.selecionou = function(selecao) {
    }
    
    this.cancelou = function () {
    	focarnoultimo();		
    }
    
    this.funcao = function(){
    }
 } 

var pesquisar;
pesquisar = new objpesquisar("pesquisar");
	
function show(campos, classe, largura, altura, retorno, id, evento ) {
	
    pesquisar.campo = id;
    pesquisar.retorno = retorno;
    pesquisar.titulo = evento;
    pesquisar.funcao = pesquisafuncaovazia;
    pesquisar.selecionou = show_retorno;
	pesquisar.executarx(classe, largura, altura, id, campos);	
}

function show_retorno(retorno) {

	var valores = pesquisar.titulo.split(":");
	
	if (valores[0] == 2) {
		show_setarconteudo(valores[1],retorno);
	} else {		
		if (valores[0] == 1) {
			submeterx(0,valores[1], true,'','','','')
		} else {
			ajaxid(pesquisar.retorno,pesquisar.campo,valores[1],'');
		}
	}
	
}

function show_setarconteudo(campo, retorno) {

    //Seta valor no campo indicado
	setValuex(campo, retorno);	

	//Executa evento onchange do campo setado
    elemento = document.forms[0].elements[campo];
	if (elemento) {
       if (elemento.onchange) {
          elemento.onchange();
       }
}
	setFocusx(campo);

}

function pesquisa(consulta, campo1, campo2, campo3, campo4, campo5, campo6, campo7) {
 
 if (campo2) {
	  pesquisar.campo = campo1;
	  pesquisar.retorno = campo2;
	  if (campo3) {
	     //Executa funcao apos setar campo
	     pesquisar.funcao = campo3;
	  } else {
	     //Executa funcao vazia apos setar campo
	     pesquisar.funcao = pesquisafuncaovazia;
	  }
      pesquisar.selecionou = pesquisa_retorno;
    } else {
 	   pesquisar.campo = "";
	   pesquisar.retorno = "";
       pesquisar.funcao = pesquisafuncaovazia;
       pesquisar.selecionou = campo1;
    }
    if (campo4){
    	pesquisar.titulo = campo4;
    }
    pesquisar.executar(consulta, campo5, campo6, campo7);
}

function pesquisafuncaovazia() {
}

function pesquisa_retorno(retorno) {

    elemento = document.forms[0].elements[pesquisar.campo];

    //Seta valor no campo indicado
	setValuex(pesquisar.campo, getValor(retorno, pesquisar.retorno));	

	//Seta foco no elemento
	//if (elemento) {
	//    try {
//			elemento.focus();
	//		elemento.select();
	 //   } catch(e) {} 
	//}

	//Executa evento onchange do campo setado
	if (elemento) {
       if (elemento.onchange) {
          elemento.onchange();
       }
    }

    //Executa funcao adicional
	pesquisar.funcao();

	setFocusx(pesquisar.campo);
	
}



		
 