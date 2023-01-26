class CustomerUpdaterService < BaseService
  attr_reader :user, :id, :name, :document_number, :phone, :state_inscription, :customer_type

  def initialize(user:, update_customer_params:)
    @user = user
    @id = update_customer_params[:id]
    @name = update_customer_params[:name]
    @document_number = update_customer_params[:document_number]
    @phone = update_customer_params[:phone]
    @state_inscription = update_customer_params[:state_inscription]
    @customer_type = Customer.customer_types[update_customer_params[:customer_type]]
  end

  def call
    customer = Customer.find(id)

    authorize!(CustomerPolicy, :update?, customer)

    customer.update!(name:, document_number:, phone:, state_inscription:, customer_type:)
    customer
  end
end
