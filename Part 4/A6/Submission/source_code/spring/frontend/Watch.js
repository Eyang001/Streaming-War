//backend api
const watchapi = "http://localhost:8080/service/watch";

//Buttons - watch
const Btn_watch = document.getElementById("watch");

//addEventListener - watch
Btn_watch.addEventListener("click", handleWatch);

//handler - watch
function handleWatch() {
  const demographicGroupShortName = document.getElementById("demo_group_watch").value;
  const percent = document.getElementById("percentage").value;
  const streamingServiceShortName = document.getElementById("stream_watch").value;
  const eventName = document.getElementById("event_name_watch").value;
  const yearProduced = document.getElementById("year_produced_watch").value;
  //remove input value on the UI
  clearwatch();

  //validate input value
  const validate = validate_watch(demographicGroupShortName, percent, streamingServiceShortName, eventName, yearProduced);
  if (validate === -1) return;

  //post to api
  const percentage = parseInt(percent);
  const data = { demographicGroupShortName, percentage, streamingServiceShortName, eventName, yearProduced};
  const options = {
    method: "POST",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(watchapi, options);
}

//validators
function validate_watch(demo, perc, stream, event, year) {
  if (demo == "" || perc == "" || stream === "" || event == "" || year == "") {
    alert(
      "Demo Group, Percentage, Streaming servie, Event Name and Year Produced can not be empty."
    );
    return -1;
  } else if (isNaN(Number(year)) || Number(year) < 0 || Number(year) > 2021) {
    alert(
      "Year input is not valid, please enter a positive number no larger than 2021"
    );
    return -1;
  } else if (isNaN(Number(perc)) || Number(perc) < 0 || Number(perc) > 100) {
    alert("Percentage input invalid. Must be a positive number that is no larger than 100.");
    return -1;
  }
}

function clearwatch() {
  document.getElementById("demo_group_watch").value = "";
  document.getElementById("percentage").value = "";
  document.getElementById("stream_watch").value = "";
  document.getElementById("event_name_watch").value = "";
  document.getElementById("year_produced_watch").value = "";
}
