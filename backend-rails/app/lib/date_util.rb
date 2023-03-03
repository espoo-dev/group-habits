module DateUtil
  def self.parse_date(date)
    DateTime.strptime(date, '%d/%m/%Y') if date
  end
end
