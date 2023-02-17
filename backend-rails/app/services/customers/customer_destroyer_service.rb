module Customers
  class CustomerDestroyerService < BaseService
    attr_reader :customer_id

    def call
      customer = Customer.find(resource_id)

      authorize!(CustomerPolicy, :destroy?, customer)

      customer.destroy!
      nil
    end
  end
end
