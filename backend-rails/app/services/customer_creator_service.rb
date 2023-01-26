class CustomerCreatorService < BaseService
  attr_reader :user, :name, :document_number, :phone, :state_inscription, :customer_type

  def initialize(user:, create_customer_params:)
    @user = user
    @name = create_customer_params[:name]
    @document_number = create_customer_params[:document_number]
    @phone = create_customer_params[:phone]
    @state_inscription = create_customer_params[:state_inscription]
    @customer_type = Customer.customer_types[create_customer_params[:customer_type]]
  end

  def call
    customer = Customer.new(user:, name:, document_number:, phone:, state_inscription:, customer_type:)

    authorize!(CustomerPolicy, :create?, customer)

    customer.save!
    customer
  end
end
