module Customers
  class CustomerUpdaterService < BaseService
    attr_reader :id, :params

    def initialize(user:, params:)
      @user = user
      @id = params[:id]
      @params = params.dup
      @params[:customer_type] = Customer.customer_types[params[:customer_type]]
    end

    def call
      customer = Customer.find(id)

      authorize!(CustomerPolicy, :update?, customer)

      customer.update!(params)
      customer
    end
  end
end
