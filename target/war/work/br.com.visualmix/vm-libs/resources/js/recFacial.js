
function initCamera(){ 
	
    try {
    		let acessoCameraManager = null;
     
            if (acessoCameraManager != null && acessoCameraManager._is_fallback_mode == false)
                acessoCameraManager.StopCamera();	

            var options = {
                showFrame: true,
                orientation: "landscape",
                width: 640,
                height: 360,
                allowFallbackNativeCamera: true,
                allowFallbackImport: true,
                facingMode: "environment",
                btnCamera_onClick: function (base64) {
                	
                    $("#srcimgCaptura").attr('width', "640");
                    $("#srcimgCaptura").attr('height', "360");    

                    $("#srcimgCaptura").attr('src', base64);
                },
                import_onChange: function (base64) {
                    $("#srcimgCaptura").attr('width', "640");
                    $("#srcimgCaptura").attr('height', "360");

                    $("#srcimgCaptura").src = base64;
                }
            }

            acessoCameraManager = new AcessoCameraManager($('.panel')[0], options);
            acessoCameraManager.InitCamera();
                        
        } catch (ex) {
            //$('#error').html(ex);
			console.log(ex);
		}            
}


function objRecFacial(){
	
	this.classToRecall;
	this.callBackMethod;
	
	this.executar = function(title){
		parent.showframe('br/com/visualmix/generico/jsprecfacial/Event.jsp?titulo=' + title,1300, 465);		
	}
	
	this.capturou = function(captura){}
	
	
}

var recFacial 
recFacial = new objRecFacial();

function fillAttrAndCallFrame(title, classToRecall, callBackMethod){
	recFacial.callBackMethod = callBackMethod;
	recFacial.classToRecall = classToRecall;
	recFacial.capturou = captura_retorno;
	
	recFacial.executar(title);
}


function captura_retorno(base64) {	
	 ajaxid(recFacial.classToRecall,'', recFacial.callBackMethod, base64,'','','',true);
}




