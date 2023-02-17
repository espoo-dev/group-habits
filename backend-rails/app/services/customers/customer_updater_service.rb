module Customers
  class CustomerUpdaterService < BaseService
    attr_reader :id, :update_customer_params

    def initialize(user:, update_customer_params:)
      @user = user
      @id = update_customer_params[:id]
      @update_customer_params = update_customer_params.dup
      @update_customer_params[:customer_type] = Customer.customer_types[update_customer_params[:customer_type]]
    end

    def call
      customer = Customer.find(id)

      authorize!(CustomerPolicy, :update?, customer)

      customer.update!(update_customer_params)
      customer
    end
  end
end
