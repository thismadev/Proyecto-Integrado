/* eslint-disable react/jsx-no-undef */
/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react'
import { deleteService, getAllServices } from '../utils/ApiFunctions'

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

    const handlePaginationClick = (pageNumber) => {
        setCurrentPage(pageNumber)
    }

    const handleDelete = async (serviceId) => {
        try {
            const result = await deleteService(serviceId)
            if(result === "") {
                setSuccessMessage(`Service ${serviceId} was deleted successfully`)
                fetchServices()
            } else {
                console.error(`Error deleting service : ${result.message}`)
            }
        } catch (error) {
            setErrorMessage(error.message)
        }
        setTimeout(() => {
            setSuccessMessage("")
            setErrorMessage("")
        }, 3000)
    }

    const calculateTotalPages = (filteredServices, servicesPerPage, services) => {
        const totalServices = filteredServices.length > 0 ? filteredServices.length : services.length
        return Math.ceil(totalServices / servicesPerPage)
    }

    const indexOfLastService = currentPage * servicesPerPage
    const indexOfFirstService = indexOfLastService - servicesPerPage
    const currentServices = filteredServices.slice(indexOfFirstService, indexOfLastService)


  return ( 
    <>
    {isLoading ? (
        <p>Loading existing services</p>
    ) : (
        <>
        <section className='mt-5 mb-5 container'>
            <div className='d-flex justify-content-center mb-3 mt-5'>
                <h2>Existing services</h2>
            </div>
            <Col md={6} className='mb-3 mb-md-0'>
                <ServiceFilter data= {services} setFilteredData={setFilteredServices}/>
            </Col>
            <table className='table table-bordered table-hover'>
                <thead>
                    <tr className='text-center'>
                        <th>ID</th>
                        <th>Service Type</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    {currentServices.map((service) => (
                        <tr key={service.id} className='text-center'>
                            <td>{service.id}</td>
                            <td>{service.serviceType}</td>
                            <td>{service.price}</td>
                            <td className='gap-2'>
                                <Link to={`/edit-service/${service.id}`}>
                                    <span className='btn btn-info btn-sm'>
                                        <FaEye/>
                                    </span>
                                    <span className='btn btn-warning btn-sm'>
                                        <FaEdit/>
                                    </span>
                                </Link>

                                <button 
                                    className='btn btn-dander btn-sm'
                                    onClick={() => handleDelete(service.id)}>
                                    <FaTrashAlt/>
                                    </button> 
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <ServicePaginator 
                currentPage={currentPage} 
                totalPages={calculateTotalPages(filteredServices,servicesPerPage,services)}
                onChange={handlePaginationClick}/>
        </section>
        </>
    )}
    </>
  )
}

export default ExistingServices