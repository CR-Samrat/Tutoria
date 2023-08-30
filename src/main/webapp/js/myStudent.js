/**
 * 
 */
head = [
    {
        class: "Class A",
        start: "10:30",
        end: "11:30",
        subject: "physics"
    },
    {
        class: "Class B",
        start: "10:30",
        end: "11:30",
        subject: "physics"
    }
]
stdData=[
    {
        name:"ss aa",
        dob:"11/22/4152",
        gender:"M",
        class:10,
        address:"Somewhere",
        pin:741247
    },
    {
        name:"ssasd aaasd",
        dob:"11/28792/4152",
        gender:"F",
        class:1054,
        address:"Somewasdhere",
        pin:741247
    },
    {
        name:"ss aa",
        dob:"11/22/4152",
        gender:"M",
        class:10,
        address:"Somewhere",
        pin:741247
    }
]
function data(t){
    var temp=""
    stdData.map((e)=>{
        temp= temp +`
        <tr>
            <td>${e.name}</td>
            <td>${e.dob}</td>
            <td>${e.gender}</td>
            <td>${e.class}</td>
            <td>${e.address}</td>
            <td>${e.pin}</td>
        </tr>
        `
    })
    return(temp);
}
let container = document.getElementById("container");
container.innerHTML = ""
var className="";
head.map((e)=>{
    var template = ""
    className = className + "a"
    template = `<div class="accordion" id="accordionExample">
    <div class="accordion-item">
      <h2 class="accordion-header" id="headingOne">
        <button class="accordion-button collapsed " name="{e.}" type="button" data-bs-toggle="collapse" data-bs-target="#${className}" aria-expanded="true" aria-controls="collapseOne">
          <div class="d-flex justify-content-between w-100">
            <div class="p-2">Class : ${e.class}</div>
            <div class="p-2">Start: ${e.start}</div>
            <div class="p-2">End: ${e.end}</div>
            <div class="p-2">Subject : ${e.subject}</div>
        </div>
        </button>
      </h2>
      <div id="${className}" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
        <div class="accordion-body">
            <table class="example" class="hover" style="width:100%">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Date Of Birth</th>
                        <th>Gender</th>
                        <th>Class</th>
                        <th>Address</th>
                        <th>Pin</th>
                    </tr>
                    <tbody>
                        ${
                           data(e.class)
                        }
                    </tbody>
                </thead>
                <tbody>
                <tfoot>
                    <tr>
                        <th>Name</th>
                        <th>Date Of Birth</th>
                        <th>Gender</th>
                        <th>Class</th>
                        <th>Address</th>
                        <th>Pin</th>
                    </tr>
                </tfoot>
            </table>
        </div>
      </div>
    </div>
    </div>`
    container.innerHTML += template
})