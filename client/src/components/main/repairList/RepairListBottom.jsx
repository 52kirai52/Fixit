function RepairListBottom() {

  const columns = [
    { label: '', width: '40px' },
    { label: '접수일시', width: '150px' },
    { label: '고객명', width: '100px' },
    { label: '차량번호', width: '120px' },
    { label: '차종', width: '120px' },
    { label: '상태', width: '100px' },
    { label: '출고일시', width: '150px' },
    { label: '상세보기', width: '80px' },
  ]

  const thStyle = {
    padding: '4px 8px',
    fontSize: '12px',
    fontWeight: 'bold',
    borderBottom: '2px solid #c0d0e0',
    borderRight: '1px solid #c0d0e0',
    backgroundColor: '#ECE9D8',
    textAlign: 'center',
    userSelect: 'none'
  }

  return (
    <div style={{ height: '540px', overflow: 'auto' }}>
      <table style={{ width: '100%', borderCollapse: 'collapse', tableLayout: 'fixed' }}>
        <thead>
          <tr>
            <th style={{ ...thStyle, width: '40px' }}>
              <input type="checkbox" />
            </th>
            {columns.slice(1).map((col, i) => (
              <th key={i} style={{ ...thStyle, width: col.width }}>
                {col.label}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {/* 추후 데이터 */}
        </tbody>
      </table>
    </div>
  )
}

export default RepairListBottom