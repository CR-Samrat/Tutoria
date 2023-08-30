/**
 * 
 */
 axios.get("tchData").then((response)=>{
	if(response.data != null){
		var el = document.createElement("option");
        el.textContent = response.data.primary
        el.value = response.data.primary;
        document.getElementById("subject").appendChild(el);
        var el1 = document.createElement("option");
        el1.textContent = response.data.secondary
        el1.value = response.data.secondary;
        document.getElementById("subject").appendChild(el1);
	}
})
