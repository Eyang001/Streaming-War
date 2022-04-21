import React from 'react'

export default function Demo({demo}) {
  return (
    <div>
      {demo.shortName, demo.longName, demo.numberOfAccounts}
    </div>
  )
}
