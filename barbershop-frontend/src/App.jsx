/* eslint-disable no-unused-vars */
import AddService from './components/services/AddService'
import ExistingServices from './components/services/ExistingServices'
import { BrowserRouter as Router, Routers, Route, Routes } from 'react-router-dom'
import './App.css'
import Home from './components/home/Home'
import EditService from './components/services/EditService'

function App() { 

  return (
    <>
    <main>
      <Router>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/edit-service/:serviceId' element={<EditService/>}/>
          <Route path='/existing-services' element={<ExistingServices/>}/>
        </Routes>
      </Router>
    </main>
    </>
  )
}

export default App
