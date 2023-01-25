class CustomerPresenter < BasePresenter
  attr_accessor :customer

  def initialize(customer)
    @customer = customer

    super()
  end

  def payload
    {
      id: customer.id,
      name: customer.name,
      document_number: customer.document_number,
      phone: customer.phone,
      state_inscription: customer.state_inscription,
      customer_type: customer.customer_type,
    }
  end
end
