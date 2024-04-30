/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react'
import { getAllServices } from '../utils/ApiFunctions'

const ExistingServices = () => {
    const[services, setServices] = useState([])
    const[currentPage, setCurrentPage] = useState(1)
    const[servicesPerPage] = useState(8)
    const[isLoading, setIsLoading] = useState(false)
    const[filteredServices, setFilteredServices] = useState([])
    const[selectedServiceType, setSelectedServiceType] = useState("")
    const[successMessage, setSuccessMessage] = useState("")
    const[errorMessage, setErrorMessage] = useState("")

    useEffect(() => {
        fetchServices()
    }, [])

    const fetchServices = async() => {
        setIsLoading(true)
        try {
            const result = await getAllServices()
            setServices(result)
            setIsLoading(false)
        } catch (error) {
            setErrorMessage(error.message)
        }
    }

    useEffect(() =>{
        if(selectedServiceType === "") {
            setFilteredServices(services)
        } else {
            const filtered = services.filter((service) => service.serviceType === selectedServiceType)
            setFilteredServices(filtered)
        }
        setCurrentPage(1)
    }, [services, selectedServiceType])

    const calculateTotalPages = (filteredServices, servicesPerPage, services) => {
        const totalServices = filteredServices.length > 0 ? filteredServices.length : services.length
        return Math.ceil(totalServices / servicesPerPage)
    }

    const indexOfLastService = currentPage * servicesPerPage
    const indexOfFirstService = indexOfLastService - servicesPerPage
    const currentServices = filteredServices.slice(indexOfFirstService, indexOfLastService)


  return ( 
    <>
    
    </>
  )
}

export default ExistingServices