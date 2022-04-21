//backend api
const studioapi = "http://localhost:8080/crud/studio";

//Buttons - studio
const createBtn_studio = document.getElementById("create_studio_btn");
const displayBtn_studio = document.getElementById("display_studio_btn");

//addEventListener - studio
createBtn_studio.addEventListener("click", handleCreateStudio);
displayBtn_studio.addEventListener("click", handleDisplayStudio);

//handler - studio
function handleCreateStudio() {
  const shortName = document.getElementById("shortName_studio").value;
  const longName = document.getElementById("longName_studio").value;
  clearstudio();

  const validate = validator_studio(shortName, longName);
  if (validate === -1) return;

  //post to api
  const data = { shortName, longName };
  const options = {
    method: "POST",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(studioapi, options);
}

function handleDisplayStudio() {
  const name = document.getElementById("shortName_studio").value;
  clearstudio();
  if (name === "") {
    alert("Short Name can not be empty.");
    return;
  }
  let table = document.getElementById("table_studio");
  table.innerHTML = "";

  fetch(studioapi)
    .then((resp) => resp.json())
    .then((data) => {
      for (let i = 0; i < data.length; i++) {
        if (data[i].shortName === name) {
          const tr =
            "<td>" +
            data[i].shortName +
            "</td><td>" +
            data[i].longName +
            "</td><td>" +
            data[i].current_mon_fee_collected +
            "</td><td>" +
            data[i].prev_mon_fee_collected +
            "</td><td>" +
            (data[i].ltd_fee_collected - data[i].current_mon_fee_collected) +
            "</td>";
          let row = table.insertRow(-1);
          row.innerHTML = tr;
        }
      }
    });
}

function validator_studio(shortName, longName) {
  if (
    shortName === "" ||
    longName === "" ||
    shortName.length > longName.length
  ) {
    alert("Name input invalid");
    return -1;
  }
}

function clearstudio() {
  
  document.getElementById("shortName_studio").value = "";
  document.getElementById("longName_studio").value = "";

}
