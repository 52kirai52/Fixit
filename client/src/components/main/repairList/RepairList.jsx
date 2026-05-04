import { useState } from 'react'
import RepairListTop from './RepairListTop'
import RepairListBottom from './RepairListBottom'
import RepairForm from './RepairForm'

function RepairList() {
  const [isFormOpen, setIsFormOpen] = useState(false)

  return (
    <div style={{
      position: 'relative',
      width: '1040px',
      height: '700px',
      margin: '18px auto',
      backgroundColor: 'white',
      border: '2px inset #c0d0e0',
    }}>
      <RepairListTop onOpenForm={() => setIsFormOpen(true)} />
      <RepairListBottom />
      {isFormOpen && <RepairForm onClose={() => setIsFormOpen(false)} />}
    </div>
  )
}

export default RepairList