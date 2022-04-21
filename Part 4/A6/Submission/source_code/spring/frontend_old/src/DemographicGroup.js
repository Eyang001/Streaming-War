import React from 'react'
import Demo from './Demo'

export default function DemographicGroup({demos}) {
    return (
        
        demos.map(demo => {
            return <Demo key = {demo.id} demo = {demo}/>
        })
            

    //         {/* fetch("http://localhost:8080/crud/demographicgroup")
    // .then((res) => res.json())
    // .catch(function (error) {
    //   console.log("Error: " + error); */}
    
    )
}
