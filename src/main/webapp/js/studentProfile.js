function getData(form) {
    var formData = new FormData(form);
    return (Object.fromEntries(formData));
  }
  
  document.getElementById("stdProfile").addEventListener("submit", function (e) {
    e.preventDefault();
    axios.post("stdProfile",getData(e.target)).then((response)=>{
        console.log(response);
	})
  });

function states(id) {
    select = document.getElementById(id);
    var state = ["Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal","Andaman and Nicobar Islands","Chandigarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Lakshadweep","Puducherry"]

    for (var i = 0; i < state.length; i++) {
        var opt = state[i];
        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;
        select.appendChild(el);
    }
}
states('state')
//populate code
var submit = document.getElementById('submit')
axios.get("isProfile").then((response)=>{
				if(response.data.isProfile == true){
						console.log("teacher dashboard")
						window.location = 'http://localhost:8080/Tutoria/teacherDashboard.html';
				}
})
