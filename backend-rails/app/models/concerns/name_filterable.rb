module NameFilterable
  extend ActiveSupport::Concern

  included do
    scope :by_name_like, lambda { |name_like|
      where('name iLIKE ?', "%#{name_like}%").order(:id)
    }
  end
end
