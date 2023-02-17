module Customers
  class CustomerCreatorService < BaseService
    attr_reader :params

    def initialize(user:, params:)
      @user = user
      @params = params.merge(user:)
      @params[:customer_type] = Customer.customer_types[params[:customer_type]]
    end

    def call
      customer = Customer.new(params)

      authorize!(CustomerPolicy, :create?, customer)

      customer.save!
      customer
    end
  end
end
