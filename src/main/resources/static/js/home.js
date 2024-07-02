$(document).ready(function(){
	// Prepare the preview for profile picture
	    $("#wizard-picture").change(function(){
	        readURL(this);
	    });
	});
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            $('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	
	
	//same as permanent address
	document.getElementById('exampleSelect1').addEventListener('click',(e)=>{
		
		let sameAs=e.target.checked;
		
		if(sameAs){
				document.getElementById('pHno').value=document.getElementById('Hno').value;
				document.getElementById('pcol').value=document.getElementById('col').value;
				document.getElementById('par').value=document.getElementById('ar').value;
				document.getElementById('pland').value=document.getElementById('land').value;
				document.getElementById('pct').value=document.getElementById('ct').value;
				document.getElementById('pst').value=document.getElementById('st').value;
				document.getElementById('pctr').value=document.getElementById('ctr').value;
				document.getElementById('ppin').value=document.getElementById('pin').value;
		}else{
			document.getElementById('pHno').value="";
				document.getElementById('pcol').value="";
				document.getElementById('par').value="";
				document.getElementById('pland').value="";
				document.getElementById('pct').value="";
				document.getElementById('pst').value="";
				document.getElementById('pctr').value="";
				document.getElementById('ppin').value="";
		}
	})