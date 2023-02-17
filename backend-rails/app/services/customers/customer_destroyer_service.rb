module Customers
  class CustomerDestroyerService < BaseService
    attr_reader :user, :customer_id

    def initialize(user:, params:)
      @user = user
      @customer_id = params[:id]
    end

    def call
      customer = Customer.find(customer_id)

      authorize!(CustomerPolicy, :destroy?, customer)

      customer.destroy!
      nil
    end
  end
end
