import "./App.css";
import React, { useState, useRef } from "react";
import DemographicGroup from "./DemographicGroup";

function App() {
  //needs to be tuned for state to work
  const [demos, setDemos] = useState([
    // fetch("http://localhost:8080/crud/demographicgroup")
    //   .then((res) => res.json())
    //   .catch(function (error) {
    //     console.log("Error: " + error);
    //   }),
  ]);

  //DemoGroup Event userRef hook setup
  const demoShortNameRef = useRef();
  const demoLongNameRef = useRef();
  const demoNumberOfAccountsRef = useRef();
  //created for testing - should remove before pushing to production
  const demoList = [];

  //create demo
  function handleCreateDemo(e) {
    const shortName = demoShortNameRef.current.value;
    const longName = demoLongNameRef.current.value;
    const numberOfAccounts = demoNumberOfAccountsRef.current.value;
    if (validator(shortName, longName, numberOfAccounts) === -1) return;

    //log inputs - should remove before pushing to production
    console.log(shortName, longName, numberOfAccounts);
    demoList.push(shortName);
    console.log(JSON.stringify(demoList));
  }

  //update demo
  function handleUpdateDemo(e) {
    const shortName = demoShortNameRef.current.value;
    const longName = demoLongNameRef.current.value;
    const numberOfAccounts = demoNumberOfAccountsRef.current.value;
    if (validator(shortName, longName, numberOfAccounts) === -1) return;
    else if (/* needs to check whether shortName exists*/ shortName === 1) {
      alert(shortName + "does not exist");
      return;
    }
    //log inputs - should remove before pushing to production
    console.log(shortName, longName, numberOfAccounts);
    demoList.push(shortName);
    console.log(JSON.stringify(demoList));
  }

  //delete demo
  function handleDeleteDemo(e) {
    const shortName = demoShortNameRef.current.value;
    const longName = demoLongNameRef.current.value;
    const numberOfAccounts = demoNumberOfAccountsRef.current.value;
    if (validator(shortName, longName, numberOfAccounts) === -1) return;
    if (/* needs to check whether shortName exists*/ shortName === 1) {
      alert(shortName + "does not exist");
      return;
    }
    //log inputs - should remove before pushing to production
    console.log(shortName, longName, numberOfAccounts);
    demoList.push(shortName);
    console.log(JSON.stringify(demoList));
  }

  return (
    <>
      <DemographicGroup demos={demos} />
      <h2> Demographic Group</h2>
      <p>Short Name</p>
      <input type="text" className="shortName" ref={demoShortNameRef} />
      <p>Long Name</p>
      <input type="text" className="longName" ref={demoLongNameRef} />
      <p>Number of Accounts</p>
      <input
        type="text"
        className="numberOfAccounts"
        ref={demoNumberOfAccountsRef}
      />
      <p>Actions</p>
      <button onClick={handleCreateDemo}>Create</button>
      <button onClick={handleUpdateDemo}>Update</button>
      <button onClick={handleDeleteDemo}>Delete</button>
      <button> Display </button>
    </>
  );

  //validate  input type - use overload to handle other classes
  function validator(shortName, longName, num) {
    if (
      shortName === "" ||
      longName === "" ||
      shortName.length > longName.length
    ) {
      alert("Name input invalid");
      return -1;
    } else if (num === "" || isNaN(Number(num))) {
      alert("Number of Accounts input invalid");
      return -1;
    }
  }
}

export default App;

{
}
