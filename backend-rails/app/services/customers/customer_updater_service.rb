module Customers
  class CustomerUpdaterService < BaseService
    def initialize(user:, params:)
      @user = user
      @params = params.dup
      @params[:customer_type] = Customer.customer_types[params[:customer_type]]
    end

    def call
      customer = Customer.find(resource_id)

      authorize!(CustomerPolicy, :update?, customer)

      customer.update!(params)
      customer
    end
  end
end
