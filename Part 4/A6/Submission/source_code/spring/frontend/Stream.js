//backend api
const streamapi = "http://localhost:8080/crud/streamingservice";

//Buttons - stream
const createBtn_stream = document.getElementById("create_stream_btn");
const updateBtn_stream = document.getElementById("update_stream_btn");
const displayBtn_stream = document.getElementById("display_stream_btn");

//addEventListener - stream
createBtn_stream.addEventListener("click", handleCreateStream);
updateBtn_stream.addEventListener("click", handleUpdateStream);
displayBtn_stream.addEventListener("click", handleDisplayStream);

//handler - stream
function handleCreateStream() {
  const shortName = document.getElementById("shortName_stream").value;
  const longName = document.getElementById("longName_stream").value;
  const subfee = document.getElementById("subscription_price").value;
  //remove input value on the UI
  clearstream();

  //validate input value
  const validate = validator_stream(shortName, longName, subfee);
  if (validate === -1) return;

  //post to api
  const subscriptionPrice = parseInt(subfee);
  const data = { shortName, longName, subscriptionPrice };
  const options = {
    method: "POST",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(streamapi, options);
}

function handleUpdateStream() {
  const shortName = document.getElementById("shortName_stream").value;
  const longName = document.getElementById("longName_stream").value;
  const subfee = document.getElementById("subscription_price").value;
  //remove input value on the UI
  clearstream();

  //validate input value
  const validate = validator_stream(shortName, longName, subfee);
  if (validate === -1) return;

  //put to api
  const subscriptionPrice = parseInt(subfee);
  const data = { shortName, longName, subscriptionPrice };
  const options = {
    method: "PUT",
    mode: "cors",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(streamapi, options);
}

function handleDisplayStream() {
  const name = document.getElementById("shortName_stream").value;
  clearstream();

  if (name === "") {
    alert("Short Name can not be empty.");
    return;
  }

  let table = document.getElementById("table_stream");
  table.innerHTML = "";

  fetch(streamapi)
    .then((resp) => resp.json())
    .then((data) => {
      for (let i = 0; i < data.length; i++) {
        if (data[i].shortName == name) {
          const tr =
            "<td>" +
            data[i].shortName +
            "</td><td>" +
            data[i].longName +
            "</td><td>" +
            data[i].subscriptionPrice +
            "</td><td>" +
            data[i].current_mon_rev +
            "</td><td>" +
            data[i].prev_mon_rev +
            "</td><td>" +
            (data[i].ltd_rev - data[i].current_mon_rev) +
            "</td><td>" +
            data[i].ltd_license_fee +
            "</td>";
          let row = table.insertRow(-1);
          row.innerHTML = tr;
        }
      }
    });
}

function validator_stream(shortName, longName, num) {
  if (
    shortName === "" ||
    longName === "" ||
    shortName.length > longName.length
  ) {
    alert("Name input invalid");
    return -1;
  } else if (num === "" || isNaN(Number(num))) {
    alert("Subscription price input invalid. Must be a number.");
    return -1;
  }
}

function clearstream() {
    document.getElementById("shortName_stream").value = "";
    document.getElementById("longName_stream").value = "";
    document.getElementById("subscription_price").value = "";
  }
  