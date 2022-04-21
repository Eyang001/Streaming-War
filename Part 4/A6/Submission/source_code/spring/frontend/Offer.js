//backend api
const offerapi = "http://localhost:8080/service/offer";

//Buttons - offer
const offerBtn_movie = document.getElementById("offer_movie");
const offerBtn_ppv = document.getElementById("offer_ppv");
//const delBtn_movie = document.getElementById("del_movie_btn");

//addEventListener - offer
offerBtn_movie.addEventListener("click", handleOfferMovie);
offerBtn_ppv.addEventListener("click", handleOfferPPV);
//delBtn_movie.addEventListener("click", handleRetractMovie);

//handler - offer
function handleOfferMovie() {
  const streamingServiceName = document.getElementById("stream_offer").value;
  const eventName = document.getElementById("event_name_offer").value;
  const yearProduced = document.getElementById("year_produced_offer").value;
  //remove input value on the UI
  clearoffer();

  //validate input value
  const validate = validator_offer_movie(streamingServiceName, eventName, yearProduced);
  if (validate === -1) return;

  //post to api
  const data = { streamingServiceName, eventName, yearProduced };
  const options = {
    method: "POST",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(offerapi, options);
}

function handleOfferPPV() {
  const streamingServiceName = document.getElementById("stream_offer").value;
  const eventName = document.getElementById("event_name_offer").value;
  const yearProduced = document.getElementById("year_produced_offer").value;
  const view_price = document.getElementById("view_price").value;
  //remove input value on the UI
  clearoffer();

  //validate input value
  const validate = validator_offer_ppv(streamingServiceName, eventName, yearProduced, view_price);
  if (validate === -1) return;

  //post to api
  const price = parseInt(view_price)
  const data = { streamingServiceName, eventName, yearProduced, price };
  const options = {
    method: "POST",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(data),
  };
  fetch(offerapi, options);
}

// function handleRetractMovie() {
//     const streamingServiceName = document.getElementById("stream_offer").value;
//     const eventName = document.getElementById("event_name_offer").value;
//     const yearProduced = document.getElementById("year_produced_offer").value;
    
//     //remove input value on the UI
//     clearoffer();
  
//     const validate = validator_offer_movie(streamingServiceName, eventName, yearProduced);
//     if (validate === -1) return;
  
//     const data = { streamingServiceName, eventName, yearProduced };
//     const options = {
//       method: "DELETE",
//       headers: { "Content-type": "application/json" },
//       body: JSON.stringify(data),
//     };
//     fetch(offerapi, options);
//   }

//validators
function validator_offer_movie(stream, movie, year) {
  if (stream === "" || movie == "" || year == "") {
    alert("Streaming servie, Event Name and Year Produced can not be empty.");
    return -1;
  } else if (isNaN(Number(year)) || Number(year) < 0 || Number(year) > 2021) {
    alert(
      "Year input is not valid, please enter a positive number no larger than 2021"
    );
    return -1;
  }
}

function validator_offer_ppv(stream, movie, year, view_price) {
  if (stream === "" || movie == "" || year == "" || view_price == "") {
    alert(
      "Streaming servie, Event Name, Year Produced and View Price can not be empty."
    );
    return -1;
  } else if (isNaN(Number(year)) || Number(year) < 0 || Number(year) > 2021) {
    alert(
      "Year input is not valid, please enter a positive number no larger than 2021"
    );
    return -1;
  } else if (isNaN(Number(view_price)) || Number(view_price) < 0) {
    alert("View Price input invalid. Must be a positive number.");
    return -1;
  }
}

function clearoffer() {
  document.getElementById("stream_offer").value = "";
  document.getElementById("event_name_offer").value = "";
  document.getElementById("year_produced_offer").value = "";
  document.getElementById("view_price").value = "";
}
