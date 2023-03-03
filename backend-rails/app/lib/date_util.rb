module DateUtil
  def self.parse_date(date)
    DateTime.strptime(date, '%d/%m/%Y') if date
  end

  def self.format_date(date)
    date&.strftime('%d/%m/%Y')
  end
end
