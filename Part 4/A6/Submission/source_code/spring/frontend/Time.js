// //backend api
// const getmonthapi = "http://localhost:8080/crud/nextmonth/display";
// const setmonthapi = "http://localhost:8080/crud/nextmonth";
//
// //Button - next month
// const curr_mon_Btn = document.getElementById("curr_month");
// const next_mon_Btn = document.getElementById("next_month");
//
// //addEventListener - next month
// curr_mon_Btn.addEventListener("click", setCurrentMonth);
// next_mon_Btn.addEventListener("click", handleNextMonth);
//
// //Handler - timedata configure
// function setCurrentMonth() {
//   const month = document.getElementById("month").value;
//   const year = document.getElementById("year").value;
//   cleartime();
//   const validate = validator_monthyear(month, year);
//   if (validate === -1) return;
//
//   let currentTime = {};
//   fetch(getmonthapi)
//     .then((response) => response.json())
//     .then((data) => {
//       currentTime = data[data.length - 1];
//       //test
//       console.log(currentTime);
//
//       if (typeof currentTime === "undefined") {
//         const newData = {
//           ...currentTime,
//           year: parseInt(year),
//           month: parseInt(month),
//         };
//         const options = {
//           method: "POST",
//           mode: "cors",
//           headers: { "Content-type": "application/json" },
//           body: JSON.stringify(newData),
//         };
//         fetch(setmonthapi, options);
//         console.log(data);
//       }
//     });
// }
//
// function handleNextMonth() {
//   cleartime();
//   fetch(getmonthapi)
//     .then((response) => response.json())
//     .then((data) => {
//       const mon = data[0].month;
//       const yr = data[0].year;
//       //test
//       console.log("data" + data);
//       console.log("month" + mon);
//       console.log("year" + yr);
//     });
//
//   const validate = validator_nextmonthyear(mon, yr);
//   if (validate === -1) return;
//
//   let month = Number(mon);
//   let year = Number(yr);
//   if (month === 12) {
//     month = 1;
//     year++;
//   } else {
//     month++;
//   }
//
//   const data = { year, month };
//   const options = {
//     method: "POST",
//     mode: "cors",
//     headers: { "Content-type": "application/json" },
//     body: JSON.stringify(data),
//   };
//   fetch(setmonthapi, options);
// }
//
// function validator_monthyear(month, year) {
//   if (month === "" || year === "") {
//     alert("Month or Year can not be empty");
//     return -1;
//   } else if (isNaN(Number(month)) || isNaN(Number(year))) {
//     alert("Month and Year have to be numbers");
//     return -1;
//   } else if (
//     Number(month) < 0 ||
//     Number(month) > 12 ||
//     Number(year) < 0 ||
//     Number(year) > 2021
//   ) {
//     alert("Month or Year input invalid.");
//     return -1;
//   }
// }
//
// function validator_nextmonthyear(month, year) {
//   if (
//     month === "" ||
//     year === "" ||
//     isNaN(Number(month)) ||
//     isNaN(Number(year)) ||
//     Number(month) < 0 ||
//     Number(month) > 12 ||
//     Number(year) < 0 ||
//     Number(year) > 2021
//   )
//     return -1;
// }
//
// function cleartime() {
//   document.getElementById("month").value = "";
//   document.getElementById("year").value = "";
// }
