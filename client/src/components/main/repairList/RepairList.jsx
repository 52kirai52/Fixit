import RepairListTop from './RepairListTop'
import RepairListBottom from './RepairListBottom'

function RepairList() {
  return (
    <div style={{
      width: '1040px',
      height: '700px',
      margin: '18px auto',
      backgroundColor: 'white',
      border: '2px inset #c0d0e0',
    }}>
      <RepairListTop />
      <RepairListBottom />
    </div>
  )
}

export default RepairList