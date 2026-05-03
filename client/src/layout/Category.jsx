function Category({ activeMenu, setActiveMenu }) {

  const menuStyle = (key) => ({
    padding: '4px 8px',
    fontSize: '12px',
    cursor: 'pointer',
    backgroundColor: activeMenu === key ? '#d0e4f7' : 'transparent',
    color: '#000',
    borderRadius: '2px',
    userSelect: 'none'
  })

  return (
    <div style={{ 
      backgroundColor: '#ECE9D8',
      border: '2px solid #0054E3',
      borderRadius: '8px 8px 0 0',
      boxShadow: '2px 2px 4px rgba(0,0,0,0.3)',
      width: '180px',
      height: '596px',
      overflow: 'hidden'
    }}>
      <div style={{ background: 'linear-gradient(to bottom, #0054E3, #0831D9)', height: '28px', display: 'flex', alignItems: 'center', paddingLeft: '8px' }}>
        <span style={{ color: 'white', fontSize: '12px', fontWeight: 'bold' }}>카테고리</span>
      </div>

      <div style={{ padding: '4px' }}>
        <div onClick={() => setActiveMenu('repairList')} style={menuStyle('repairList')}>
          📁 정비 관리
        </div>
      </div>

    </div>
  )
}

export default Category