class SalesUnitPresenter < BasePresenter
  attr_accessor :sales_unit

  def initialize(sales_unit)
    @sales_unit = sales_unit

    super()
  end

  def payload
    {
      id: sales_unit.id,
      name: sales_unit.name
    }
  end
end
