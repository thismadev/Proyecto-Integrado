/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react'
import { getServiceType } from '../utils/ApiFunctions'

const ServiceTypeSelector = ({handleServiceInputChange, newService}) => {
    const[ServiceType, setServiceType] = useState([])
    const[showNewServiceTypeInput, setShowNewServiceTypeInput] = useState(false)
    const[newServiceType, setNewServiceType] = useState("")

    useEffect(() => {
        getServiceType().then((data) => {
            setServiceType(data)
        })
    }, [])

    const handleNewServiceTypeInputChange = (e) => {
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
    <>
    {ServiceType.length > 0 && (
    <div>
      <select name="serviceType" id="serviceType" value={newService.ServiceType} 
      onChange={(e) => {
        if(e.target.value === "Add New"){
          setShowNewServiceTypeInput(true)
        } else {
          handleServiceInputChange(e)
        }
      }}>
        <option value={""}> Select a service type </option>
        <option value={"Add New"}> Add new </option>
        {ServiceType.map((type, index) => (
        <option key={index} value={type}>
          {type}
        </option>
        ))}
      </select>

      {showNewServiceTypeInput && (
      <div className='input-group'>
        <input className='form-control' type="text" placeholder='Enter a new service type' onChange={handleNewServiceTypeInputChange}/>
        <button className='btn btn-hotel' type='button' onClick={handleAddNewServiceType}>Add</button>

      </div>
      )}
    </div>
    )}
    </>
  )
}

export default ServiceTypeSelector
