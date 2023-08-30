function subjects(id) {
    select = document.getElementById(id);
    var arr = ['Agriculture', 'Anthropology', 'Archaeology', 'Architecture and Design', 'Area Studies', 'Arts (Drawing)', 'Business', 'Chemistry', 'Computer Basics', 'Computer Sciences', 'Cultural and Ethnic Studies', 'Divinity', 'Earth Sciences', 'Economics', 'Education', 'Engineering', 'English', 'Environmental Studies and Forestry', 'Family and Consumer Science', 'Gender and Sexuality Studies', 'Geography', 'Health Sciences', 'History', 'Human Physical Performance and Recreation', 'Journalism', 'Languages and linguistics', 'Law', 'Library and Museum Studies', 'Life Sciences', 'Literature', 'Logic', 'Mathematics', 'Maths', 'Media Studies and Communication', 'Military Sciences', 'Performing arts', 'Philosophy', 'Physical Education', 'Physics', 'Political Science', 'Psychology', 'Public Administration', 'Regional Language(s)', 'Religion and Religious studies', 'Science', 'Social Sciences', 'Social Work', 'Sociology', 'Space Sciences', 'Statistics', 'Systems Science', 'Transportation', 'Visual arts']

    for (var i = 0; i < arr.length; i++) {
        var opt = arr[i];
        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;
        select.appendChild(el);
    }
}
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
subjects('preferedSubjectPrimary')
subjects('preferedSubjectSecondary')
states('state')
//populate code

var primary = document.getElementById('preferedSubjectPrimary')
var secondary = document.getElementById('preferedSubjectSecondary')
var submit = document.getElementById('submit')
var errorMessage = document.getElementById("errorMessage")
primary.addEventListener("change", (e) => {
    console.log('primary : ' + primary.value)
    console.log('secondary : ' + secondary.value)
    if (primary.value == secondary.value) {
        submit.disabled = true;
        errorMessage.innerHTML = "Primary and Secondary Subject Cant be same"
    } else {
        submit.disabled = false;
        errorMessage.innerHTML = '';
    }
})
secondary.addEventListener("change", (e) => {
    if (primary.value == secondary.value) {
        submit.disabled = true;
        errorMessage.innerHTML = "Primary and Secondary Subject Cant be same"
    } else {
        submit.disabled = false;
        errorMessage.innerHTML = '';
    }
})
axios.get("isProfile").then((response)=>{
				if(response.data.isProfile == true){
						console.log("teacher dashboard")
						window.location = 'http://localhost:8080/Tutoria/teacherDashboard.html';
				}
})
