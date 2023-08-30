function modal(cId,id){
	var modalTemplate = `<a href="" class="stretched-link" data-bs-toggle="modal" data-bs-target="#${id}">
                            Edit Timing
                        </a>
                       <div class="modal fade" id="${id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                          <form action="ClassTiming" method="post">
                            <div class="modal-header">
                              <h5 class="modal-title" id="exampleModalLabel">Edit Class Timing</h5>
                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                
                                    <div class="container">
                                    <input type="hidden" name="cId" value="${cId}">
                                        <label for="check">Weekdays</label>
                                        <div class="checkbox-group" id="check">
                                            <div>
                                                <input class="form-check-input" type="checkbox" id="Monday"
                                                name="weekday" value="monday"><label for="Monday"
                                                class="mx-2 h4">Monday</label>
                                                <div class="row mx-3">
                                                    <div class="form-group col ">
                                                        <label for="sTime">Start Time</label> <input type="time"
                                                            class="form-control" id="sTime" name="smondayTime"
                                                            placeholder="Start Time" >
                                                    </div>
                                                    <div class="form-group col">
                                                        <label for="eTime">End Time</label> <input type="time"
                                                            class="form-control" id="eTime" name="emondayTime"
                                                            placeholder="End Time">
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div>
                                                <input class="form-check-input"
                                                type="checkbox" id="Tuesday" name="weekday" value="tuesday"><label
                                                for="Tuesday" class="mx-2 h4">Tuesday</label>
                                                <div class="row mx-3">
                                                    <div class="form-group col ">
                                                        <label for="sTime">Start Time</label> <input type="time"
                                                            class="form-control" id="sTime" name="stuesdayTime"
                                                            placeholder="Start Time">
                                                    </div>
                                                    <div class="form-group col">
                                                        <label for="eTime">End Time</label> <input type="time"
                                                            class="form-control" id="eTime" name="etuesdayTime"
                                                            placeholder="End Time">
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div>
                                                <input class="form-check-input" type="checkbox" id="Wednesday"
                                                name="weekday" value="wednesday"><label for="Wednesday"
                                                class="mx-2 h4">Wednesday</label>
                                                <div class="row mx-3">
                                                    <div class="form-group col ">
                                                        <label for="sTime">Start Time</label> <input type="time"
                                                            class="form-control" id="sTime" name="swednesdayTime"
                                                            placeholder="Start Time">
                                                    </div>
                                                    <div class="form-group col">
                                                        <label for="eTime">End Time</label> <input type="time"
                                                            class="form-control" id="eTime" name="ewednesdayTime"
                                                            placeholder="End Time">
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div>
                                                <input class="form-check-input"
                                                type="checkbox" id="Thursday" name="weekday" value="thursday"><label
                                                for="Thursday" class="mx-2 h4">Thursday</label>
                                                <div class="row mx-3">
                                                    <div class="form-group col ">
                                                        <label for="sTime">Start Time</label> <input type="time"
                                                            class="form-control" id="sTime" name="sthursdayTime"
                                                            placeholder="Start Time">
                                                    </div>
                                                    <div class="form-group col">
                                                        <label for="eTime">End Time</label> <input type="time"
                                                            class="form-control" id="eTime" name="ethursdayTime"
                                                            placeholder="End Time">
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div>
                                                <input class="form-check-input" type="checkbox" id="Friday"
                                                name="weekday" value="friday"><label for="Friday"
                                                class="mx-2 h4">Friday</label>
                                                <div class="row mx-3">
                                                    <div class="form-group col ">
                                                        <label for="sTime">Start Time</label> <input type="time"
                                                            class="form-control" id="sTime" name="sfridayTime"
                                                            placeholder="Start Time">
                                                    </div>
                                                    <div class="form-group col">
                                                        <label for="eTime">End Time</label> <input type="time"
                                                            class="form-control" id="eTime" name="efridayTime"
                                                            placeholder="End Time">
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div>
                                                <input class="form-check-input"type="checkbox" id="Saturday" name="weekday" value="saturday"><label
                                                for="Saturday" class="mx-2 h4">Saturday</label> 
                                                <div class="row mx-3">
                                                    <div class="form-group col ">
                                                        <label for="sTime">Start Time</label> <input type="time"
                                                            class="form-control" id="sTime" name="ssaturdayTime"
                                                            placeholder="Start Time">
                                                    </div>
                                                    <div class="form-group col">
                                                        <label for="eTime">End Time</label> <input type="time"
                                                            class="form-control" id="eTime" name="esaturdayTime"
                                                            placeholder="End Time">
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div>
                                                <input class="form-check-input" type="checkbox" id="Sunday"
                                                name="weekday" value="sunday"><label for="Sunday"
                                                class="mx-2 h4">Sunday</label>
                                                <div class="row mx-3">
                                                    <div class="form-group col ">
                                                        <label for="sTime">Start Time</label> <input type="time"
                                                            class="form-control" id="sTime" name="ssundayTime"
                                                            placeholder="Start Time">
                                                    </div>
                                                    <div class="form-group col">
                                                        <label for="eTime">End Time</label> <input type="time"
                                                            class="form-control" id="eTime" name="esundayTime"
                                                            placeholder="End Time">
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>    
                                        </div>
                                    </div>
                                
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                              <button type="submit" class="btn btn-primary">Save changes</button>
                            </form>
                            </div>
                          </div>
                        </div>
                      </div>`
	return modalTemplate
}

axios.get("getClass").then((response)=>{
	console.log(response)
	document.getElementById("tableBody").innerHTML="";
	var id = "";
	response.data.jsonarray.map((e)=>{
		id=id+"a";
		if(e.timeset == 0){
			var template = `
					<tr>
						<td>${e.cName}</td>
						<td>${e.subject}</td>
						<td><a href="">View Details</a></td>
						<td>${modal(e.cId,id)}</a></td>
						
					</tr>
		`
		}else{
			var template = `
					<tr>
						<td>${e.cName}</td>
						<td>${e.subject}</td>
						<td><a href="">View Details</a></td>
						
					</tr>
		`
		}
		
		document.getElementById("tableBody").innerHTML += template
	})
})