module Customers
  class CustomerDestroyerService < DestroyerService
    def prepare_resource
      Customer.find(resource_id)
    end
  end
end
