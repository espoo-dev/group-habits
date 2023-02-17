module Customers
  class CustomerCreatorService < BaseService
    attr_reader :create_customer_params

    def initialize(user:, create_customer_params:)
      @user = user
      @create_customer_params = create_customer_params.merge(user:)
      @create_customer_params[:customer_type] = Customer.customer_types[create_customer_params[:customer_type]]
    end

    def call
      customer = Customer.new(create_customer_params)

      authorize!(CustomerPolicy, :create?, customer)

      customer.save!
      customer
    end
  end
end
