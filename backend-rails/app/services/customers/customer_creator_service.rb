module Customers
  class CustomerCreatorService < BaseService

    def initialize(user:, params:)
      @user = user
      @params = params.dup
      @params[:customer_type] = Customer.customer_types[params[:customer_type]]
    end

    def call
      customer = Customer.new(params_with_user)

      authorize!(CustomerPolicy, :create?, customer)

      customer.save!
      customer
    end
  end
end
