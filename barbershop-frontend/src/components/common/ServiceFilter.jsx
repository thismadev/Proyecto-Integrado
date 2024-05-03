/* eslint-disable no-unused-vars */
import React, { useState } from 'react'

export const ServiceFilter = (data, setFilteredData) => {
    const[filter, setFilter] = useState("")
    const handleSelectChange = (e) => {
        const selectedServiceType = e.target.value
        setFilter(selectedServiceType)
        const filteredServices = data.filter((service) => 
        service.serviceType.toLowercase()
        .include(selectedServiceType.toLowercase()))
        setFilteredData(filteredServices)
    }
    const clearFilter = () => {
        setFilter("")
        setFilteredData(data)
    }

    const serviceTypes = ["", ...new Set(data.map((service) => service.serviceType))]

  return (
    <div className='input-group mb-3'>
        <span className='input-group-text' id='service-type-filter'> 
          Filter services by type
        </span>
        <select className='form-select' 
        aria-label="romm type filter"
        value={filter} 
        onChange={handleSelectChange} name="" id="">
            <option value={""}>Select a service type to filter...</option>
            {serviceTypes.map((type, index) => (
            <option key={index} value={String(type)}>
                {String(type)}
            </option>
            ))}
        </select>
        <button className='btn btn-hotel' type='button' onClick={clearFilter}>Clear filter</button>
    </div>
  )
}

export default ServiceFilter
