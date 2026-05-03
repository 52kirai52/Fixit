import RepairList from '../components/main/repairList/RepairList'

function Main({ activeMenu }) {
  return (
    <div style={{
      backgroundColor: '#ECE9D8',
      border: '2px solid #0054E3',
      borderRadius: '8px 8px 0 0',
      boxShadow: '2px 2px 4px rgba(0,0,0,0.3)',
      width: '1076px',
      height: '764px',
      overflow: 'hidden'
    }}>
      <div style={{ background: 'linear-gradient(to bottom, #0054E3, #0831D9)', height: '28px', display: 'flex', alignItems: 'center', paddingLeft: '8px' }}>
        <span style={{ color: 'white', fontSize: '12px', fontWeight: 'bold' }}>메인</span>
      </div>

      {activeMenu === 'repairList'
        ? <RepairList />
        : null
      }

    </div>
  )
}

export default Main