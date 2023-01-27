class CustomerDestroyerService < BaseService
  attr_reader :user, :customer_id

  def initialize(user:, destroy_customer_params:)
    @user = user
    @customer_id = destroy_customer_params[:id]
  end

  def call
    customer = Customer.find(customer_id)

    authorize!(CustomerPolicy, :destroy?, customer)

    customer.destroy!
    nil
  end
end
