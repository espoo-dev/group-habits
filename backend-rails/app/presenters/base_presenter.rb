class BasePresenter
  attr_accessor :category

  def self.payload_for_item(item)
    new(item).payload
  end

  def self.payload_for_list(items)
    items.map do |item|
      payload_for_item(item)
    end
  end
end
