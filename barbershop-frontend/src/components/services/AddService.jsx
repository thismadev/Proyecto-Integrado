/* eslint-disable no-unused-vars */
import React, { useState } from 'react'
import { addService } from '../utils/ApiFunctions'

const AddService = () => {
    const[newService, setNewService] = useState({
        serviceType: "",
        price: ""
    })

    const[successMessage, setSuccessMessage] = useState("")
    const[errorMessage, setErrorMessage] = useState("")

    const handleServiceInputChange = (e) => {
        const name = e.target.name
        let value = e.target.value
        if(name === "price") {
            if(!isNaN(value)) {
            value.parseInt(value)
        } else {
            value = ""
        }
    }
    setNewService({...newService, [name]: value})
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
        const success = await addService(newService.serviceType, newService.price)
        if(success !== undefined) {
            setSuccessMessage("A new service was added to the database")
            setNewService({serviceType: "", price: ""})
            setErrorMessage("")
        } else {
           setErrorMessage("Error adding a service") 
        }
    } catch (error) {
        setErrorMessage(error.message)
    }
  }

  return (
    <>
    <section className='container, mt-5 mb-5'>
        <div className='row justify-content-center'>
            <div className='col-md-8 col-lg-6'>
                <h2 className='mt-5 mb-5'>Add a service</h2>
                <form onSubmit={handleSubmit}>
                    <div className='mb-3'>
                        <label htmlFor='serviceType' className='form-label'>
                            Service Type
                        </label>
                        <input type="text" className='form-control' required id='serviceType' name='serviceType' value={newService.serviceType} onChange={handleServiceInputChange}/>
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='price' className='form-label'>
                            Price
                        </label>
                        <input type="number" className='form-control' required id='price' name='price' value={newService.price} onChange={handleServiceInputChange}/>
                    </div>

                    <div className='d-grid d-md-flex mt-2'>
                        <button className='btn btn-outline-primary ml-5'>
                            Save service
                        </button>
                    </div>

                </form>
            </div>
        </div>
    </section>
    </>
  )
}

export default AddService