module Customers
  class CustomerDestroyerService < BaseService

    def call
      customer = Customer.find(resource_id)

      authorize!(CustomerPolicy, :destroy?, customer)

      customer.destroy!
      nil
    end
  end
end
