//backend api
const demoapi = "http://localhost:8080/crud/demographicgroup";

//Buttons - demo
const createBtn_demo = document.getElementById("create_demo_btn");
const updateBtn_demo = document.getElementById("update_demo_btn");
const displayBtn_demo = document.getElementById("display_demo_btn");

//addEventListener - demo
createBtn_demo.addEventListener("click", handleCreateDemo);
updateBtn_demo.addEventListener("click", handleUpdateDemo);
displayBtn_demo.addEventListener("click", handleDisplayDemo);

//Handlers - demo
function handleCreateDemo() {
  const shortName = document.getElementById("shortName_demo").value;
  const longName = document.getElementById("longName_demo").value;
  const numberOfAccounts = document.getElementById("numberOfAccounts").value;
  //remove input value on the UI
  cleardemo();
  //validate input value
  const validate = validator_demo(shortName, longName, numberOfAccounts);
    if (validate === -1) return;

  //post to api
  const number_of_accounts = parseInt(numberOfAccounts);
  const data = { shortName, longName, number_of_accounts };
  const options = {
    method: "POST",
    mode: "cors",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(demoapi, options);
}

function handleUpdateDemo() {
  const shortName = document.getElementById("shortName_demo").value;
  const longName = document.getElementById("longName_demo").value;
  const numberOfAccounts = document.getElementById("numberOfAccounts").value;
  //remove input value on the UI
  cleardemo();
  
  //validate input value
  const validate = validator_demo(shortName, longName, numberOfAccounts);
  if (validate === -1) return;

  //post to api
  const number_of_accounts = parseInt(numberOfAccounts);
  const data = { shortName, longName, number_of_accounts };
  const options = {
    method: "PUT",
    mode: "cors",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(demoapi, options);
}

function handleDisplayDemo() {
  const name = document.getElementById("shortName_demo").value;
  cleardemo();

  if (name === "") {
    alert("Short Name can not be empty.");
    return;
  }

  let table = document.getElementById("table_demo");
  table.innerHTML = "";

  fetch(demoapi)
    .then((response) => response.json())
    .then((data) => {
      for (let i = 0; i < data.length; i++) {
        if (data[i].shortName == name) {
          const tr =
            "<td>" +
            data[i].shortName +
            "</td><td>" +
            data[i].longName +
            "</td><td>" +
            data[i].number_of_accounts +
            "</td><td>" +
            data[i].current_mon_spending +
            "</td><td>" +
            data[i].prev_mon_spending +
            "</td><td>" +
            (data[i].ltd_spending - data[i].current_mon_spending) +
            "</td>";
          let row = table.insertRow(-1);
          row.innerHTML = tr;
        }
      }
    });
}

function validator_demo(shortName, longName, num) {
  if (
    shortName === "" ||
    longName === "" ||
    shortName.length > longName.length
  ) {
    alert("Name input invalid");
    return -1;
  } else if (num === "" || isNaN(Number(num))) {
    alert("Number of Accounts input invalid. Must be a number.");
    return -1;
  }
}

function cleardemo() {
  document.getElementById("shortName_demo").value = "";
  document.getElementById("longName_demo").value = "";
  document.getElementById("numberOfAccounts").value = "";
}