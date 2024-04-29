/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react'
import { getServiceType } from '../utils/ApiFunctions'

const ServiceTypeSelector = () => {
    const[ServiceType, setServiceType] = useState([])
    const[showNewServiceTypeInput, setShowNewServiceTypeInput] = useState(false)
    const[newServiceType, setNewServiceType] = useState("")

    useEffect(() => {
        getServiceType().then((data) => {
            setServiceType(data)
        })
    }, [])

    const handleNewServiceInputChange = (e) => {
        setNewServiceType(e.target.value);
    }

    const handleAddNewServiceType = () => {
        if(newServiceType !== "") {
          setServiceType([...ServiceType, newServiceType])
          setNewServiceType("")
          setShowNewServiceTypeInput(false)
        }
    }

  return (
    <div>ServiceTypeSelector</div>
  )
}

export default ServiceTypeSelector
