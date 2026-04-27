function Category() {
  return (
    <div style={{ 
      backgroundColor: '#ECE9D8',
      border: '2px solid #0054E3',
      borderRadius: '8px 8px 0 0',
      boxShadow: '2px 2px 4px rgba(0,0,0,0.3)',
      overflow: 'hidden'
    }}>
      <div style={{ background: 'linear-gradient(to bottom, #0054E3, #0831D9)', height: '28px', display: 'flex', alignItems: 'center', paddingLeft: '8px' }}>
        <span style={{ color: 'white', fontSize: '12px', fontWeight: 'bold' }}>카테고리</span>
      </div>
    </div>
  )
}

export default Category