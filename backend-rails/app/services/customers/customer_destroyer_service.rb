module Customers
  class CustomerDestroyerService < DestroyerService
    def prepare_resource
      resource = Customer.find(resource_id)
    end
  end
end
