module.exports = (req, res, next) => {
  if (req.method === 'POST') {
    req.method = 'GET';
  }

  res.header('authorization', 'Bearer eyJhY2Nlc3MtdG9rZW4iOiJkcWpzUk9oN3p5d1h3aE5qdWVsbGpnIiwidG9rZW4tdHlwZSI6IkJlYXJlciIsImNsaWVudCI6IkFtQkQ2Z2g1MS1OVFFUOTdWRHE2Q1EiLCJleHBpcnkiOiIxNzMyMzE4NjQxIiwidWlkIjoidXNlckBleGFtcGxlLmNvbSJ9')
  next()
}
