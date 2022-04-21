//backend api
const eventapi = "http://localhost:8080/crud/event";

//Buttons - event
const createBtn_event = document.getElementById("create_event_btn");
const updateBtn_event = document.getElementById("update_event_btn");
const displayBtn_event = document.getElementById("display_event_btn");

//addEventListener - event
createBtn_event.addEventListener("click", handleCreateEvent);
updateBtn_event.addEventListener("click", handleUpdateEvent);
displayBtn_event.addEventListener("click", handleDisplayEvent);

//handler - event
function handleCreateEvent() {
  const type = document.getElementById("type_event").value;
  const name = document.getElementById("name_event").value;
  const yearProduced = document.getElementById("year_produced").value;
  const dura = document.getElementById("duration").value;
  const studioShortName = document.getElementById("produced_by").value;
  const lic_fee = document.getElementById("licensing_fee").value;

  //remove input value on the UI
  clearevent();

  //validate input value
  const validate = validator_event(
    type,
    name,
    yearProduced,
    dura,
    studioShortName,
    lic_fee
  );
  if (validate === -1) {
    return;
  }

  //post to api
  const duration = parseInt(dura);
  const licensingFee = parseInt(lic_fee);
  const messageType = type;
  const data = {
    type,
    name,
    yearProduced,
    duration,
    studioShortName,
    licensingFee,
    messageType,
  };
  const options = {
    method: "POST",
    mode: "cors",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(eventapi, options);
  console.log(data);
}

function handleUpdateEvent() {
  const name = document.getElementById("name_event").value;
  const yearProduced = document.getElementById("year_produced").value;
  const dur = document.getElementById("duration").value;
  const licensing_fee = document.getElementById("licensing_fee").value;
  //remove input value on the UI
  clearevent();

  //validate user input
  const validate = validator_event(name, yearProduced, dur, licensing_fee);
  if (validate === -1) return;

  //get the event to be updated
  let getEvent = {};

  //get
  fetch(eventapi)
    .then((resp) => resp.json())
    .then((data) => {
      for (let i = 0; i < data.length; i++) {
        if (data[i].name == name && data[i].yearProduced == yearProduced) {
          getEvent = data[i];
          //deconstruct getEvent and replace property value with new data
          const newdata = {
            ...getEvent,
            duration: parseInt(dur),
            licensingFee: parseInt(licensing_fee),
          };
          const options = {
            method: "PUT",
            mode: "cors",
            headers: { "Content-type": "application/json" },
            body: JSON.stringify(newdata),
          };
          fetch(eventapi, options);
          break;
        }
      }
    });
}

function handleDisplayEvent() {
  clearevent();

  let table = document.getElementById("table_event");
  table.innerHTML = "";

  fetch(eventapi)
    .then((resp) => resp.json())
    .then((data) => {
      for (let i = 0; i < data.length; i++) {
        const tr =
          "<td>" +
          data[i].type +
          "</td><td>" +
          data[i].name +
          "</td><td>" +
          data[i].yearProduced +
          "</td><td>" +
          data[i].duration +
          "</td><td>" +
          data[i].studioShortName +
          "</td><td>" +
          data[i].licensingFee +
          "</td>";
        let row = table.insertRow(-1);
        row.innerHTML = tr;
      }
    });
}

function validator_event(
  type,
  name,
  year,
  duration,
  producedby,
  licensing_fee
) {
  if (type != "movie" && type != "ppv") {
    alert("Type has to be either movie or ppv.");
    return -1;
  } else if (name === "") {
    alert("Name invalid.");
    return -1;
  } else if (
    year === "" ||
    isNaN(Number(year)) ||
    Number(year) < 0 ||
    Number(year) > 2021
  ) {
    alert("Production year invalid.");
    return -1;
  } else if (producedby === "") {
    alert("Producer name invalid");
    return -1;
  } else if (
    duration === "" ||
    licensing_fee === "" ||
    isNaN(Number(duration)) ||
    isNaN(Number(licensing_fee))
  ) {
    alert("Duration or Licensing Fee input invalid. Must be numbers.");
    return -1;
  }
}

function validator_event(stream, name, year) {
  if (stream === "" || name === "" || year === "") {
    alert("Streaming Service, Movie name and Year produced cannot be empty");
    return -1;
  } else if (isNaN(Number(year)) || Number(year) < 0 || Number(year) > 2021) {
    alert("Production year invalid.");
    return -1;
  }
}

function validator_event(name, year_produced, duration, licensing_fee) {
  if (
    name === "" ||
    year_produced === "" ||
    duration === "" ||
    licensing_fee === ""
  ) {
    alert(
      "Event name, Year produced, Duration and Licensing Fee cannot be empty"
    );
    return -1;
  } else if (isNaN(Number(duration)) || isNaN(Number(licensing_fee))) {
    alert("Duration and Licensing Fee input invalid. Must be numbers");
    return -1;
  }
}

function clearevent() {
  document.getElementById("type_event").value = "";
  document.getElementById("name_event").value = "";
  document.getElementById("year_produced").value = "";
  document.getElementById("duration").value = "";
  document.getElementById("produced_by").value = "";
  document.getElementById("licensing_fee").value = "";
}
